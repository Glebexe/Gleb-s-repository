local util = require("util")

local function showGui(player)
  local frame = player.gui.relative.add({
    type = "frame",
    name = "main",
    direction = "vertical",
    anchor = {
      gui = defines.relative_gui_type.production_gui,
      position = defines.relative_gui_position.right
    }
  })
  buildTitlebar(frame)
  frame.style.minimal_width = 352

  local innerFrame = frame.add({
    type = "frame",
    style = "inside_deep_frame"
  })

  local scrollPanel = innerFrame.add({
    type = "scroll-pane"
  })
  scrollPanel.style.padding = { 4, 4, 4, 4 }
  scrollPanel.style.vertically_stretchable = true

  local sortedPotential = getProductionSortedByPotential(player.force)
  local items = {}
  for _, item in ipairs(sortedPotential) do
    local flow = scrollPanel.add({
      type = "flow",
      direction = "horizontal"
    })
    flow.style.vertical_align = "center"
    flow.add({
      type = "sprite",
      sprite = (item.type == "item" and "item/" or "fluid/") .. item.name,
      tooltip = (item.type == "item" and game.item_prototypes or game.fluid_prototypes)[item.name].localised_name
    })
    local bar = flow.add({
      type = "progressbar",
      style = "statistics_progressbar",
      value = item.potential
    })
    bar.style.horizontally_stretchable = true
    local label = flow.add({
      type = "label",
      style = "electric_usage_label",
      caption = util.format_number(item.actual_per_minute, true) .. "/m"
    })
    label.style.horizontal_align = "right"
    table.insert(items, { name = item.name, gui = flow })
  end
  global[player.index] = global[player.index] or {}
  global[player.index].items = items
end

function buildTitlebar(frame)
  local flow = frame.add({
    name = "titlebar",
    type = "flow",
    direction = "horizontal",
  })
  flow.add({
    type = "label",
    style = "frame_title",
    caption = "Potential",
    ignored_by_interaction = true
  })
  flow.style.horizontal_spacing = 8
  flow.style.bottom_padding = 4

  local filler = flow.add({
    type = "empty-widget",
    style = "draggable_space_header",
    ignored_by_interaction = true
  })
  filler.style.right_margin = 4
  filler.style.height = 24
  filler.style.horizontally_stretchable = true
end

function getProductionSortedByPotential(force)
  local potentialProduction = getPotentialProduction(force)
  local sortedPotential = {}
  for _, item in pairs(potentialProduction) do
    local actualPerMinute = getActualProduction(force, item.name, item.type)
    table.insert(sortedPotential, {
      name = item.name,
      type = item.type,
      actual_per_minute = actualPerMinute,
      potential = actualPerMinute / (item.potential_per_second * 60)
    })
  end
  table.sort(sortedPotential, function(item1, item2)
    return item1.potential > item2.potential
  end)
  return sortedPotential
end

function getPotentialProduction(force)
  local potentialProduction = {}
  local function addProducts(products, productivityBonus, seconds)
    for _, output in pairs(products) do
      local productAmount = util.product_amount(output)
      local amountPerSecond = (productAmount * (1 + productivityBonus)) / seconds
      if amountPerSecond > 0 then
        if not potentialProduction[output.name] then
          potentialProduction[output.name] = {
            name = output.name,
            type = output.type,
            potential_per_second = 0
          }
        end
        potentialProduction[output.name].potential_per_second = amountPerSecond + potentialProduction[output.name].potential_per_second
      end
    end
  end

  for _, surface in pairs(game.surfaces) do
    local entities = surface.find_entities_filtered({type = {"furnace", "assembling-machine"}, force = force})
    for _, machine in pairs(entities) do
      local recipe = machine.get_recipe()
      if recipe and not recipe.hidden_from_flow_stats then
        local recipeTime = recipe.energy
        local actualTime = recipeTime / machine.crafting_speed
        local productivityBonus = machine.productivity_bonus
        addProducts(recipe.products, productivityBonus, actualTime)
      end
    end

    local mining_entities = surface.find_entities_filtered({type = {"mining-drill"}, force = force})
    for _, miner in pairs(mining_entities) do
      if miner.mining_target then
        local mineable_properties = miner.mining_target.prototype.mineable_properties
        local recipeTime = mineable_properties.mining_time
        local craftingSpeed = miner.prototype.mining_speed
        local actualTime = recipeTime / craftingSpeed
        local productivityBonus = miner.productivity_bonus
        addProducts(mineable_properties.products, productivityBonus, actualTime)
      end
    end
  end
  return potentialProduction
end

function getActualProduction(force, name, type)
  local statistics
  if type == "item" then
    statistics = force.item_production_statistics
  elseif type == "fluid" then
    statistics = force.fluid_production_statistics
  else
    return 0
  end
  return statistics.get_flow_count({
    name = name,
    input = true,
    precision_index = defines.flow_precision_index.one_minute,
     count = true
  })
end

script.on_event(defines.events.on_gui_opened, function(event)
  if event.gui_type == defines.gui_type.production then
    local player = game.get_player(event.player_index)
    local frame = player.gui.relative["main"]
    if not frame or not frame.valid then
      showGui(player)
    end
  end
end)

script.on_event(defines.events.on_gui_closed, function(event)
  if event.gui_type == defines.gui_type.production then
    local player = game.get_player(event.player_index)
    local frame = player.gui.relative["main"]
    if frame and frame.valid then
      frame.destroy()
    end
  end
end)
