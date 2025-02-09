import { writable } from 'svelte/store';
import taskService from '../services/taskService';

const createTaskStore = () => {
    const { subscribe, set } = writable([]);

    return {
        subscribe,
        fetchTasks: async () => {
            const tasks = await taskService.getTasks();
            set(tasks);
        },
        addTask: async (task) => {
            await taskService.createTask(task);
            const tasks = await taskService.getTasks();
            set(tasks);
        },
        deleteTask: async (id) => {
            await taskService.deleteTask(id);
            const tasks = await taskService.getTasks();
            set(tasks);
        },
        updateTask: async (id, updatedTask) => {
            await taskService.updateTask(id, updatedTask);
            const tasks = await taskService.getTasks();
            set(tasks);
        },
        updateTaskStatus: async (id, status) => {
            await taskService.updateTaskStatus(id, status);
            const tasks = await taskService.getTasks();
            set(tasks);
        }
    };
};

export const tasks = createTaskStore();