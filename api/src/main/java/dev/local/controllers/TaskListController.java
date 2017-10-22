package dev.local.controllers;

import dev.local.controllers.wrappers.SwapList;
import dev.local.domain.TaskList;
import dev.local.services.TaskListService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 任务分组的API，由于任务分组只是一个项目中的逻辑上的分类，因此不提供列表API
 * 需要列表可以取得项目信息，在项目中会有该项目的列表信息。
 */
@RestController
@RequestMapping("/taskLists")
@PreAuthorize("hasRole('USER')")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class TaskListController {

    private final TaskListService service;

    @RequestMapping(method = RequestMethod.GET)
    public List<TaskList> findByProjectId(@RequestParam(value = "projectId") String projectId) {
        return service.findByProjectId(projectId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/swapOrder")
    public List<TaskList> swapOrder(@RequestBody SwapList list) {
        return service.swapOrder(list.getSrcListId(), list.getTargetListId());
    }

    @RequestMapping(method = RequestMethod.POST)
    public TaskList add(@RequestBody TaskList list){
        return service.add(list);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public TaskList findById(@PathVariable String id) {
        return service.findById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public TaskList update(@PathVariable String id, @RequestBody TaskList group){
        group.setId(id);
        return service.update(group);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public TaskList delete(@PathVariable String id){
        return service.delete(id);
    }
}
