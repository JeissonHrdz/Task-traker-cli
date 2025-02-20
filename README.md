
# CLI Task Tracker

It is an application used through the CLI (command line) which allows you to manage tasks. In this project you can add new tasks, modify their status and/or description and delete them. In addition, you can list all the tasks that have been created, and also list them by the status they are in.

Below you will see the commands to use the application.

***NOTE***: All commands must be entered in lowercase.




**Add a new task**
```diff
add "New Task"
```

**List all tasks**
```diff
list
```


**List tasks for status**
```diff
list todo
list in-progress
list done
```

The number in te next commands refers to the task id, which can be seen when we use the **list** command.

**Upadate status**
```diff
mark-in-progress 1 
mark-done 1
```

**Upadate description**
```diff
update 1 "New Description"
```

**Delete task**
```diff
delete 1
```