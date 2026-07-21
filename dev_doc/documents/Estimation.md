# Project Estimation

Project Estimation for InventoryApp

Date Completed: 2026-07-21

Date Last Update: 2026-07-21

### Estimation approach

Estimates should not be considered particularly accurate as it is a 1-person-project with no fixed work schedule.

__Measuring Units Legend__:
- ph = Person Hours (1 ph = 1 person working 1 hour)
- pd = Person Days (1 pd = 8 ph)
- pw = Person Weeks (1 pw = 7 pd = 56 ph) (in this particular case)
- LOC = Lines of Code
- Euro (€) = Euro currency
- Calendar Time = [w = week, d = day, h = hour]


## Estimate by Size

__Legend__:
- Estimated Number of Classes (NC) => Classes to be developed
- Estimated Average Size per Class (A) => Average size of a class, in LOC
- Estimated Size of Project (S) => $NC * A$
- Estimated Effort (E) => $S / (10 \frac{LOC}{h})$ (Pessimistic estimate of 10 LOC/h)
- Estimated Cost (C) => $E * 0€$ (Is a personal project)
- __Estimated Total Time (Effort)__ (Tot) $E / (TeamSize * 8 \frac{h}{day} * 7 \frac{days}{week})$ (Working 7 days a week)
- __Estimated Calendar Time__ => $Tot / TeamSize$ (In this case, TeamSize = 1)

| Metric                            | Estimate |
|:----------------------------------|:---------|
| Estimated Number of Classes       |          |
| Estimated Average Size per Class  |          |
| Estimated Size of Project         |          |
| Estimated Effort                  |          |
| Estimated Cost                    |          |
| __Estimated Total Time (Effort)__ | __0__    |
| __Estimated Calendar Time__       | __0__    |

__Notes__:
- Assuming "Class" as the Java concept of "class", that is, a "module". If this is not the case, the NC estimate would be less informative as it would not include all system modules that are not classes.
- The Number of Classes is roughly estimated starting from the number of "Entities" in the project and then multiplying by the number of modules that will be created for each of the (ex: Repository, Model, View, DAO, etc.). On the top of that, the number of support modules (ex: Utils, Error, etc.) is added.
- Estimated Average Size per Class is estimated considering that the most modules are small (ex: DAO, Repository, etc.).


## Estimate by Product Decomposition

| Component Name        | Estimated Effort (ph) |
|-----------------------|-----------------------|
| Requirements Document |                       |
| Design Document       |                       |
| Code                  |                       |
| Informal Testing Docs |                       |
| Management Documents  |                       |
| __Total__             | __0__                 |

__Notes__:
- "Management Documents" are those documents in charge of managing the project (Timesheet, Estimation, etc.) plus the time spent on ALM and VC tools.


## Estimate by Activity Decomposition

__Sections Legend__:
- Planning: Concept Phase, Requirement Engineering
- Design: System Architecture Design, UI & UX Design, Database Design, System Design
- Development: Development Back-end - Entities, Development Back-end - Logic, Development Front-end
- Testing: Informal Testing
- Release: System/App Documentation

| Activity Name                   | Estimated Effort (ph) |
|:--------------------------------|:----------------------|
| Concept Phase                   |                       |
| Requirement Engineering         |                       |
| Software Architecture Design    |                       |
| UI & UX Design                  |                       |
| Database Design                 |                       |
| Software Design                 |                       |
| Development Back-end - Entities |                       |
| Development Back-end - Logic    |                       |
| Development Front-end           |                       |
| Informal Testing                |                       |
| System/App Documentation        |                       |
| __Total__                       | __0__                 |

__Notes__:
- Being an Android Project, in the GANTT Chart the Development of Back-end and Front-end are considered to be doable in parallel, as the Back-end modules cannot exist or properly work without at least part of the Front-end being present.

### GANTT Chart

<!-- ```mermaid

gantt
    title GANTT Chart (1d = 8ph; lun-sun)
    dateFormat YYYY-MM-DD
    todayMarker off
    tickinterval 1week
	weekday monday
	axisFormat Day %j


	section Planning
		Concept Phase 						:a1, 2026-07-16, 0d
		Requirement Engineering 			:a2, after a1, 0d

	section Design
		Software Architecture Design 		:b1, after a2, 0d
		UI & UX Design 						:b2, after b1, 0d
		Database Design 					:b3, after b2, 0d
		Software Design 					:b4, after b3, 0d

	section Development
		Development Back-end - Entities 	:c1, after b3, 0d
		Development Back-end - Logic 		:c2, after c1, 0d
		Development Front-end 				:c3, after b3, 0d

	section Testing
		Informal Testing 					:d1, after c3, 0d

	section Release
		System/App Documentation 			:e1, after d1, 0d

``` -->


## Summary

| Estimate Type                      | Estimated Effort | Estimated Duration |
|------------------------------------|------------------|--------------------|
| Estimate by Size                   | 0                | 0 week             |
| Estimate by Product Decomposition  | 0                | 0 week             |
| Estimate by Activity Decomposition | 0                | 0 week             |
| __Avg Estimated Effort__           | __0__            | __0 week__         |

