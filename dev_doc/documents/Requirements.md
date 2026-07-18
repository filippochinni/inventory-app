# Requirements Document - InventoryApp

Date: 2026-07-18

Version: v1 - Requirements Analysis for InventoryApp

| Version | Curr App Version | Curr Milestone | Change |
|:-------:|:-----------------|:---------------|:-------|
|   v1    | v0.1.0-alpha     | Requirements   | -      |

#### Contents

- [Requirements Document - InventoryApp](#requirements-document---inventoryapp)
	* [Informal Description](#informal-description)
	* [Business Model](#business-model)
	* [Stakeholders](#stakeholders)
	* [Requirements Elicitation](#requirements-elicitation)
		+ [Personas](#personas)
		+ [Problems](#problems)
		+ [User Stories](#needs-user-stories)
	* [Context](#context)
		+ [Actors](#actors)
		+ [Context Diagram](#context-diagram)
		+ [System Interaction Interfaces](#system-interaction-interfaces)
	* [Requirements](#requirements)
		+ [Functional Requirements](#functional-requirements)
			- [Table of Rights](#table-of-rights)
		+ [Non-Functional Requirements](#non-functional-requirements)
	* [Use Cases](#use-cases)
		+ [Use Cases Diagram](#use-cases-diagram)
		+ [Use Cases List](#use-cases-list)
			- [Use case 1 (UC1)](#use-case-1-uc1)
				* [Scenario 1.1](#scenario-11)
				* [Scenario 1.2](#scenario-12)
			- [Use case 2 (UC2)](#use-case-2-uc2)
				* [Scenario 2.1](#scenario-21)
				* [Scenario 2.2](#scenario-22)
			- [Use case 3 (UC3)](#use-case-3-uc3)
				* [Scenario 3.1](#scenario-31)
				* [Scenario 3.2](#scenario-32)
	* [System Design](#system-design-diagram)
	* [Deployment Diagram](#deployment-diagram)
	* [Glossary](#glossary)
		+ [Glossary Terms](#glossary-terms)
		+ [Glossary Diagram](#glossary-diagram)

## Informal Description

InventoryApp is a stock management (inventory, stock, stocktaking) Android application which keeps track of the location of items and provides multiple features to help find, organize and visualize items.

## Business Model

There are multiple possible business models for InventoryApp.

__Open Source__: InventoryApp could be released as an open-source project, publicly available and free to use and modify. With this model financial support could come from donations, enterprise licensing, paid technical support, hosting.

__Proprietary Software__: InventoryApp could be released as a standard proprietary software, obtainable through a one-time purchase.

__Software as a Service (SaaS)__: InventoryApp could be released as a subscription-based service, with a monthly or yearly fee to use the app.

__Freemium__: InventoryApp could be released as a free app with basic features, and lock the full functionality behind a paywall, with a one-time purchase or a subscription model.

__Adware__: InventoryApp could be released as a free app with and display ads to the user, with the possibility of removing ads through payment, combining with other business models.

__Commissioning__: InventoryApp could be proposed to a particular client while in early development, and be commissioned to be developed ad-hoc for that specific client.

## Stakeholders

| Stakeholder         | Description                                                                                                                                                |
|:--------------------|:-----------------------------------------------------------------------------------------------------------------------------------------------------------|
| User (Generic)      | Private citizen who wishes to catalogate their personal proprieties to whatever purpose (selling, separation of propriety, stocktaking, house moving)      |
| Small Businesses    | Owners of small commercial activities, like stores, who need a digitalized solution to manage their inventory and catalogate their goods                   |
| Private Companies   | Companies with a Logistic Division, or in general with logistic necessities who needs a pocket solution usable by any worker at any time in the warehouses |
| Compulsive Hoarders | Individuals who collect large quantities of unrelated items and need a way to categorize and track them                                                    |
| Collectors          | Individuals who collect items of interest and need a way to organize their collections either for tidiness or for future selling                           |
| Payment Service     | (If the App requires payment processing) The payment service for the purchase of the App license                                                           |

## Requirements Elicitation

Information collected informally during the Concept phase of the project. In this section such intel is organized and partially formalized so that it can be used in the rest of the Requirements Engineering process.

### Personas

__Persona 1 - "Nonno"__:
Man, Old, 93, Retired

He owns an assorted collection of objects, large and small, tools and utensils, furniture and machinery, basically everything. He needs a fast way to track and catalog his items.

__Persona 2 - "Filippo"__:
Man, Young, 24, Student

He has recently found his old collection of toys, soon he will have to move to a new apartment. He needs a way to catalog his items distinguishing between personal items, toys, clothes, etc.

__Persona 3 - "Maria"__:
Woman, Adult, 56, Professor

She has a large collection of books, and she needs a way to catalog them and keep track of their location in her house.

__Persona 4 - "Miriam"__:
Woman, Adult, 40, Housewife

She owns a large amount of clothes from her family, both old and currently in use. She wants to free the house from old stuff and an easy way to track and catalog clothes and other junk to be sold or donated.

__Persona 5 - "John"__:
Man, Adult, 35, Warehouse Worker

He works in a warehouse and his tired to be handled a printed list of items to be stocked and moved. He would prefer a smarter solution.


### Problems

| Problem                 | Description                                                                                                 |
|:------------------------|:------------------------------------------------------------------------------------------------------------|
| Large amount of Items   | Some people own large amounts of assorted items                                                             |
| Cannot find belongings  | People often forget where they put their things                                                             |
| Unorganized Items       | Items are often stored without a precise orden an without any form of labeling                              |
| Paper notes Stocktaking | Inventories written on pieces of paper can easily be lost or forgotten                                      |
| Stocktaking is Boring   | Many people do not like writing inventories, mainly because is a slow process                               |
| Decentralized Inventory | Both in personal and professional settings an Inventory is tied to a location and has a single access point |
| Lack of Info on Items   | Most existing stocktaking systems for small and medium stores only store the item name                      |

### Needs (User Stories)

User Stories are the translation of the Problems into Needs, they are still written informally and without using technical language, therefore they still cannot and should not be considered Requisites. 

| User Story                        | User Story Description                                                    |
|:----------------------------------|:--------------------------------------------------------------------------|
| US1 - Store Item                  | __As a__ User                                                             |                
|                                   | __I want to__ memorize an Item                                            |              
|                                   | __So that__ I can track its existence                                     |
| --------------------------        | -------------------------------------------------                         |
| US2 - Store Item Location         | __As a__ User                                                             |                
|                                   | __I want to__ memorize the main info of an Item (location, name, desc)    |              
|                                   | __So that__ I can find it                                                 |
| --------------------------        | -------------------------------------------------                         |
| US3 - Store Item Details          | __As a__ User                                                             |                
|                                   | __I want to__ memorize additional details of an Item                      |              
|                                   | __So that__ I can remeber and visualize such info later                   |
| --------------------------        | -------------------------------------------------                         |
| US4 - Store Item Pictures         | __As a__ User                                                             |                
|                                   | __I want to__ memorize one or more pictures of the item                   |              
|                                   | __So that__ I can see the Item                                            |
| --------------------------        | -------------------------------------------------                         |
| US5 - Search Item                 | __As a__ User                                                             |                
|                                   | __I want to__ search an Item                                              |              
|                                   | __So that__ I can visualize it                                            |
| --------------------------        | -------------------------------------------------                         |
| US6 - Visualize Item              | __As a__ User                                                             |                
|                                   | __I want to__ visualize all the info about on Item                        |              
|                                   | __So that__ I can see its details                                         |
| --------------------------        | -------------------------------------------------                         |
| US7 - Apply Labels                | __As a__ User                                                             |                
|                                   | __I want to__ apply one or more labels to an Item                         |              
|                                   | __So that__ I can better categorize them                                  |
| --------------------------        | -------------------------------------------------                         |
| US8 - Create new Labels           | __As a__ User                                                             |                
|                                   | __I want to__ create new Labels                                           |              
|                                   | __So that__ I can use my own categoris on items                           |
| --------------------------        | -------------------------------------------------                         |
| US9 - Visualize Items by Location | __As a__ User                                                             |                
|                                   | __I want to__ visualize a list of all the Items in a specific Location    |              
|                                   | __So that__ I can know what Items a certain Location contains             |
| --------------------------        | -------------------------------------------------                         |
| US10 - Visualize Items by Labels  | __As a__ User                                                             |                
|                                   | __I want to__ visualize a list of all the Items with specific Labels      |              
|                                   | __So that__ I can the see the Items for each category                     |
| --------------------------        | -------------------------------------------------                         |
| US11 - Create Inventory           | __As a__ User                                                             |                
|                                   | __I want to__ create new "Inventories"                                    |              
|                                   | __So that__ I can have multiple "Inventories"                             |
| --------------------------        | -------------------------------------------------                         |
| US12 - Switch Inventory           | __As a__ User                                                             |                
|                                   | __I want to__ switch between "Inventories"                                |              
|                                   | __So that__ I can operate on a different Inventory                        |
| --------------------------        | -------------------------------------------------                         |
| US13 - Update Item                | __As a__ User                                                             |                
|                                   | __I want to__ modify the details of an existing Item                      |              
|                                   | __So that__ I can update the Item details                                 |
| --------------------------        | -------------------------------------------------                         |
| US14 - Delete Item                | __As a__ User                                                             |                
|                                   | __I want to__ delete an Item from the Inventory                           |              
|                                   | __So that__ I can stop tracking the Item                                  |
| --------------------------        | -------------------------------------------------                         |
| US15 - Archive Item               | __As a__ User                                                             |                
|                                   | __I want to__ archive an Item without deleting it                         |              
|                                   | __So that__ I can keep a memento of an old or sold Item                   |
| --------------------------        | -------------------------------------------------                         |
| US16 - Save Data Remotely         | __As a__ User                                                             |                
|                                   | __I want to__ save the Inventories data remotely                          |              
|                                   | __So that__ I can access data from other devices                          |
| --------------------------        | -------------------------------------------------                         |
| US17 - Export Data                | __As a__ User                                                             |                
|                                   | __I want to__ export the Inventory data                                   |              
|                                   | __So that__ I can backup it or share it                                   |
| --------------------------        | -------------------------------------------------                         |
| US18 - Import Data                | __As a__ User                                                             |                
|                                   | __I want to__ import the Inventory data                                   |              
|                                   | __So that__ I can import a backup or data shared to me                    |
| --------------------------        | -------------------------------------------------                         |
| US19 - Visualize Statistics       | __As a__ User                                                             |                
|                                   | __I want to__ visualize some statistics about an Inventory                |              
|                                   | __So that__ I can see global information about the Inventory              |
| --------------------------        | -------------------------------------------------                         |
| US20 - Search Item on the Web     | __As a__ User                                                             |                
|                                   | __I want to__ search one of my items, either by name or image, on the web |              
|                                   | __So that__ I can get additional info on it for knowledge or selling      |
| --------------------------        | -------------------------------------------------                         |

## Context

### Actors

From Problems and Needs, no particular differences between types of users can be identified, therefore the only Actor is the User.
Every User access the same functionalities, with the same privileges, and with the same interfaces.

Actors will still be analyzed in the rest of the Requirements Document in case of evolution of the App (for example distinction between free and paid users in the case of Freemium business model).

| Actor Name | Description             |
|:-----------|:------------------------|
| BaseUser   | Anyone who uses the App |

### Context Diagram

In the case of a system with a single Actor, the Context Diagram is trivial as there is only one external entity which interacts with the system.

![Context Diagram](./_res/Context_Diagram.png)

### System Interaction Interfaces

| Actor    | Logical Interface | Physical Interface |
|:---------|:-----------------:|:------------------:|
| BaseUser |        GUI        |     Smartphone     |


## Requirements

### Functional Requirements

| ID | Description |
|:---|:------------|

#### Table of Rights

| FR | Actor |
|:--:|:-----:|

### Non-Functional Requirements

The possible types of Non-Functional Requirement are the following: Correctness, Usability, Efficiency, Reliability, Maintainability, Portability, Security, Safety, Interoperability, Ethical, Standard, Privacy, Domain.

|  ID  | Type      | Description                           | Refers to |
|:----:|:----------|:--------------------------------------|:---------:|
| NFR1 | Domain    | The App must support Italian language |     -     |
| NFR2 | Usability | The App has a Dark Mode               |     -     |

## Use Cases

### Use Cases Diagram

### Use Cases List

#### Use case 1 (UC1)

| UC1              | Use Case 1: Use Case Name | 
|:-----------------|:--------------------------|
| Actors Involved  |                           |
| Pre-condition    |                           |
| Post-condition   |                           |
| Nominal Scenario |                           |
| Variants         |                           |
| Exceptions       |                           |

##### Scenario 1.1

| UC1 - S1.1     | Scenario 1.1: Use Case Name (Scenario Spec) |
|:---------------|:--------------------------------------------|
| Pre-condition  |                                             |
| Post-condition |                                             |
| __Step#__      | <div align="center"> __Description__ </div> |
| 1              |                                             |
| 2              |                                             |
| 3              |                                             |
| 4              |                                             |

##### Scenario 1.2

#### Use case 2 (UC2)

##### Scenario 2.1

##### Scenario 2.2

#### Use case 3 (UC3)

##### Scenario 3.1

##### Scenario 3.2

## System Design Diagram

## Deployment Diagram

## Glossary

### Glossary Terms

| Term               | Description                                                                         | Synonyms              |
|:-------------------|:------------------------------------------------------------------------------------|:----------------------|
| __Inventory__      | A collection of Items indentified by Sites, Spots, Containers                       |                       |
| __Site__           | A physical place, like a building, a sector of a builing, or an appartment          |                       | 
| __Spot__           | A physical, more precise, place, like a drawer, bookshelf or warehouse shelf        |                       |
| __Container__      | An entity that can cointain Items, or whatever object that can cointain other Items |                       |
| __Item__           | An atomic piece of possession (good, material, object)                              | Object, Things, Stuff |
| __Location__       | Full path of a Location of an Item (Site>[Spots]>[Containers]>Item)                 |                       |
| __AbstractObject__ | Abstract entity, meaning either a Item or a Container                               |                       |
| __Track/Tracked__  | To add to an Inventory / To be actively part of an Inventory                        | Store, Memorize       |
| __Stocktaking__    | The physical activity of tracking or counting materials or goods                    | Inventory, Stock      |

### Glossary Diagram

