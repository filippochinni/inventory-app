# Design Document - InventoryApp

System Design Document for InventoryApp

Date Completed: 2026-08-17

Date Last Update: 2026-08-17

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

Diagram Type: Semi-Formal Diagram (mix of UML Class and UML Package)

![Software Design Diagram](./_res/Software_Design_Diagram.png)

|                |                                                                                                                   |
|----------------|-------------------------------------------------------------------------------------------------------------------|
| **Screen**     | <img src="./_res/_sw_design_zoom/Software_Design_Diagram__Screen.png" alt="SW Desing - Screen" width=700>         |
| **ViewModel**  | <img src="./_res/_sw_design_zoom/Software_Design_Diagram__ViewModel.png" alt="SW Design - ViewModel" width=700>   |
| **Domain**     | <img src="./_res/_sw_design_zoom/Software_Design_Diagram__Domain.png" alt="SW Design - Domain" width=700>         |
| **Repository** | <img src="./_res/_sw_design_zoom/Software_Design_Diagram__Repository.png" alt="SW Design - Repository" width=700> |
| **DataSource** | <img src="./_res/_sw_design_zoom/Software_Design_Diagram__DataSource.png" alt="SW Design - DataSource" width=700> |

__NOTES:__
- The Domain layer and its use-cases are not strict design specifications, depending on the complexity of the feature
  detected during the development phase, new classes may be added and current ones may be modified or removed.

### Software Sub-Components Specification

| Package / Component    | Description                                                                                                                  |
|------------------------|------------------------------------------------------------------------------------------------------------------------------|
| **MainActivity**       | Functions as the entry point of the App. Necessary in an Android App                                                         |
| **MainApplication**    | Necessary for Dependency Injection                                                                                           |
| **Screen**             | Contains the Compose Screen classes which build the UI and handle UI-specific logic                                          |
| **Theme**              | Contains the Compose Theme classes providing color, typography, and shapes to the App                                        |
| **Navigation**         | Contains the Navigation classes which manage the navigation between Screens                                                  |
| **ViewModel**          | Contains pairs of ViewModel classes and their corresponding State classes                                                    |
| **Domain**             | Contains the UseCase classes which implement the business logic of the App                                                   |
| **Repository**         | Contains groups made of a Repository interface and one or more Repository implementations                                    |
| **RepositoryDIModule** | Is a DI container which provides Repository dependencies to the Domain layer                                                 |
| ***Repository***       | Interface representing the archetype of a Repository                                                                         |
| **DataSourceDIModule** | Is a DI container which provides DataSource dependencies to the Repository layer                                             |
| **Entity**             | Contains the Entity classes which represent the data model of the App and are used by other packages of the DataSource layer |
| **Database**           | Contains the DAO classes which manage the persistence of the App data which is stored in the local database                  |
| **AppDatabase**        | Is the component which manages and represents the local database of the App                                                  |
| **DataStore**          | Contains the DataStore classes which manage the persistence of the App data which is the local filesystem                    |
| **Network**            | Contains the Network classes which manage the persistence of the App data which is stored in a remote server                 |
| **AppNetworkService**  | Is the component which manages and represents the remote server of the App                                                   |

## Database Design

### Conceptual Database Diagram

Diagram Type: Conceptual Entity-Relationship Diagram

![Conceptual Database Diagram](./_res/Database_Desing_Diagram__Conceptual.png)

### Logical Database Diagram

Diagram Type: UML Entity-Relationship Diagram

![Logical Database Diagram](./_res/Database_Design_Diagram__Logical.png)

## Release Deployment Diagram

More concrete version of the Deployment Diagram, where abstract Artifacts and Components, like external services or
execution environments, are replaced by the ones which are actually going to be implemented in the Release.

Diagram Type: UML Deployment Diagram

![Release Deployment Diagram](./_res/Release_Deployment_Diagram.png)
