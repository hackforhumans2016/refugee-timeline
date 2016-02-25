/* A collection of important and/or complex SQL queries on the timeline database */

/* Get the parents of a task */
WITH RECURSIVE TaskParents(taskId) AS (VALUES (<id>) UNION ALL SELECT Task.parentTaskRef FROM Task WHERE Task.id = TaskParents.taskId)
SELECT TaskParents.taskId FROM TaskParents;

/* Retrieve ids of conditions, referenced by a given step */
WITH OPERATORS(id, opStr) AS (
	VALUES 
	(1, "&"), 
	(2, "|"), 
	(3, "!"), 
	(4, "("), 
	(5, ")"), 
	(6, "^"), 
	(7, "=")
), 
CondFormatted(id, str) AS (
	SELECT 
	1, replace(Step.conditions, " ", "") 
	FROM Step 
	WHERE Step.id = <id> 
	UNION ALL 
	SELECT CondFormatted.id + 1, trim(replace(CondFormatted.str, OPERATORS.opStr, ","), ",") 
	FROM OPERATORS, CondFormatted
	WHERE OPERATORS.id = CondFormatted.id
), 
StepConditions(conditionId, str) AS (
	SELECT 0, CondFormatted.str
	FROM CondFormatted
	WHERE CondFormatted.id =(SELECT max(CondFormatted.id) FROM CondFormatted) 
	UNION ALL 
	SELECT substr(StepConditions.str, 1, case when instr(StepConditions.str, ",") then instr(StepConditions.str, ",") - 1 else StepConditions.str end), case when instr(StepConditions.str, ",") then substr(StepConditions.str, instr(StepConditions.str, ",") + 1) else  NULL end
	FROM StepConditions
	WHERE StepConditions.str NOT NULL
)
SELECT DISTINCT StepConditions.conditionId FROM StepConditions;

/* Get direct predecessors (Tasks) */
/* <<< Retrieve Condition ids >>> */
SELECT DISTINCT Condition.id, Condition.name FROM Condition, StepConditions WHERE Condition.id = StepConditions.conditionId AND Condition.type = "Task";

/* Get direct predecessors (Steps) */
/* <<< Retrieve Condition ids >>> */
SELECT DISTINCT Condition.id, Condition.name FROM Condition, StepConditions WHERE Condition.id = StepConditions.conditionId AND Condition.type = "Step";
