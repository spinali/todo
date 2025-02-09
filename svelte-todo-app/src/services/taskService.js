const API_URL = 'http://localhost:8081/api/tasks';

const taskService = {
    async getTasks() {
        const response = await fetch(API_URL);
        if (!response.ok) {
            throw new Error('Failed to fetch tasks');
        }
        return await response.json();
    },

    async createTask(task) {
        const response = await fetch(API_URL, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(task),
        });

        if (!response.ok) {
            throw new Error('Failed to create task');
        }
        return response.json();
    },

    async deleteTask(id) {
        const response = await fetch(`${API_URL}/${id}`, {
            method: 'DELETE',
        });

        if (!response.ok) {
            throw new Error('Failed to delete task');
        }
    },
    async updateTask(id, updatedTask) {
        const response = await fetch(`${API_URL}/${id}`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(updatedTask),
        });
        if (!response.ok) {
            throw new Error('Failed to update task');
        }
        return response.json();
    },
    async updateTaskStatus(id, status) {
        const response = await fetch(`${API_URL}/${id}`, {
            method: 'PATCH',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({completed: status}),
        });
        if (!response.ok) {
            throw new Error('Failed to update task');
        }
        return response.json();
    }
};

export default taskService;