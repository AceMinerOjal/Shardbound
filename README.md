# AetherResonance

<p align="center">
  <img src="res/AetherResonance.ico" alt="AetherResonance logo" width="256" height="256" />
</p>

AetherResonance is a top-down Java party RPG with:

- up to 4 class slots (Mage, Warrior, Tank, Priest)
- Tiled JSON maps with collision and portals
- save/load quickstates
- LAN/TCP host-client networking
- elemental skill/status system
- map-aware enemies with 10-tile sensing and pathfinding

This project was previously named `legend-java`.

## Running

Build and run with your normal Java/Maven workflow.

### Paths

- map resources: `maps/<mapId>.json`
- quicksave file: `saves/quicksave.properties`
- window icon file: `res/AetherResonance.ico` (loaded as classpath resource `AetherResonance.ico`)
- path constants source: `src/main/GamePaths.java`

Network mode is configured with JVM args:

- local (default): `--mode=local`
- LAN host: `--mode=lan-host --port=7777`
- LAN client: `--mode=lan-client --host=HOST_IP --port=7777`
- TCP host: `--mode=tcp-host --port=7777`
- TCP client: `--mode=tcp-client --host=HOST_IP --port=7777`

Renderer mode:

- Java2D (default): `--renderer=java2d`
- Vulkan shader bootstrap: `--renderer=vulkan` (falls back to Java2D if Vulkan init fails)

Networking model:

- host is authoritative
- clients send inputs
- host publishes snapshots

## Controls

Join slots:

- `F1` Mage
- `F2` Warrior
- `F3` Tank
- `F4` Priest

Controls for all classes:

- move `W A S D`
- hotbar skills `1 2 3 4`
- item modifier `Shift`

Save state:

- save quickstate: `F5`
- load quickstate: `F9`
- file: `saves/quicksave.properties`

## Classes, Elements, and Items

Class defaults:

- Mage -> `FIRE`
- Warrior -> `LIGHTNING`
- Tank -> `EARTH`
- Priest -> `ICE`

Class race/profession identities:

- Warrior (`HUMAN`) -> `FORESTING`, `WOODCUTTING`
- Tank (`DWARF`) -> `FORGING`, `MINING_ORES`, `MINING_ORBS` (orbs for wand crafting)
- Mage (`ELF`) -> `CRAFT_SCROLLS`, `CRAFT_ROBES`, `CRAFT_WANDS`
- Priest (`HALF_ELF`) -> `ALCHEMY`, `BLESSING_ENCHANTING`

Element/status behavior:

- active skill type is the current signature element (`FIRE`, `ICE`, `LIGHTNING`, `EARTH`)
- applied status effect is derived from the active element
- all classes can cycle elements at runtime

Hotbar controls:

- `skill1..skill4`: use hotbar abilities

Inventory controls:

- `modifier + skill1`: select previous inventory item
- `modifier + skill2`: use selected inventory item
- `modifier + skill3`: select next inventory item

Items:

- `ELEMENT_TUNER`: cycles active element

Leveling:

- starting level is `0`
- max level is `128`

## Map Authoring (Tiled)

Maps are loaded from classpath resources at `maps/<id>.json`.
Current default IDs are: `world`, `cave`, `dungeon`.

Collision:

- tile layer name `collision`, or layer property `collidable=true`
- solid tiles are marked in tileset tile properties with `solid=true`

Portals:

- object layer name `portals`
- each rectangle object supports:
- `targetMap` (destination map id)
- `targetX` (destination x in pixels)
- `targetY` (destination y in pixels)

Friendly fire zones:

- friendly fire is OFF by default
- enable in specific areas using object layer `friendly_fire` (or `pvp`)
- add rectangle objects to define PvP-active regions

Enemy variants:

- enemies sense players from up to 10 tiles away
- enemies spawn from walkable tile variants
- each enemy is locked to its spawn variant
- enemies pathfind only through tiles of their own variant
