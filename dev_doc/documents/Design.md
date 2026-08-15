# Design Document - InventoryApp

System Design Document for InventoryApp

Date Completed: --

Date Last Update: 2026-07-21

## UI/UX Design

UI Design choices are not definitive and may be subject to minor changes during the Front-End implementation due to the
differences in the technologies for design and development.

![UI Design Full](./_res/_ui/UI_Design_FULL.gif)

|                    |                                                                                                                                    |
|--------------------|------------------------------------------------------------------------------------------------------------------------------------|
| **Inventory**      | ![UI Inventory Management](./_res/_ui/UI_Inventory_Management.png)                                                                 |
| **Inventory View** | ![UI Inventory View](./_res/_ui/UI_Inventory_View.png)                                                                             |
| **Item Create**    | ![UI Item Create](./_res/_ui/UI_Item_Create.png)                                                                                   |  |
| **Item View**      | <img src="./_res/_ui/UI_Item_View.png" alt="UI Item View" width="250"/>                                                            |
| **Label Create**   | ![UI Label Create](./_res/_ui/UI_Label_Create.png)                                                                                 | 
| **Label Delete**   | ![UI Label Delete](./_res/_ui/UI_Label_Delete.png)                                                                                 |
| **Label Edit**     | ![UI Label Edit](./_res/_ui/UI_Label_Edit.png)                                                                                     |
| **Search Items**   | <img src="./_res/_ui/UI_Search_Items.png" alt="UI Search Items" width="750"/>                                                      |
| **Search Places**  | <img src="./_res/_ui/UI_Search_All.png" alt="UI Search All" width="284"/> <img src="./_res/_ui/UI_Search_Places.png" width="250"/> |
| **Account**        | ![UI Settings Account](./_res/_ui/UI_Settings_Account.png)                                                                         |
| **Account Edit**   | ![UI Settings Account Edit](./_res/_ui/UI_Settings_Account_Edit.png)                                                               |
| **Statistics**     | <img src="./_res/_ui/UI_Statistics.png" alt="UI Statistics" width="750"/>                                                          |

## Software Architecture Design

Architectural Pattern: Layered Architecture (Android Variant)
Relational Patterns included: Single Source of Truth (SSOT), Unidirectional Data Flow (UDF)

![Software Architecture Design](./_res/Software_Architecture_Diagram.png)

### Software Components Specification

Main Layers:

|                  |                                       |
|------------------|---------------------------------------|
| **UI Layer**     | Builds and manages the user interface |
| **Domain Layer** | Handles business logic                |
| **Data Layer**   | Obtains and exposes app data          |

Sub-Layers:

|                |                                                                  |
|----------------|------------------------------------------------------------------|
| **Screen**     | Describes the UI and handles user interactions                   |
| **ViewModel**  | Hosts and handles the UI state and manages configuration changes |
| **Repository** | Exposes the app data and centralizes data access                 |
| **DataSource** | Obtain app data and manages persistence                          |

## Software Design

### Software Modules Specification

## Database Design

## Release Deployment Diagram

More concrete version of the Deployment Diagram, where abstract Artifacts and Components, like external services or
execution environments, are replaced by the ones which are actually going to be implemented in the Release.
