<script>
    import { tasks } from '../store/taskStore';
    let isEditing = false;
    let editedTask = { id: null, title: '', description: '', dueAt: '' };
    let completedFilter = null;
    let dueAtFilter = '';

    // Formatowanie daty na europejski format
    function formatEuropeanDate(datetime) {
        if (!datetime) return '';
        const date = new Date(datetime);
        const options = {
            day: '2-digit',
            month: '2-digit',
            year: 'numeric',
            hour: '2-digit',
            minute: '2-digit',
        };
        return date.toLocaleString('pl-PL', options);
    }

    const startEdit = (task) => {
        isEditing = true;
        editedTask = { ...task };
    };

    const handleEdit = async () => {
        await tasks.updateTask(editedTask.id, editedTask);
        isEditing = false;
    };

    const handleStatus = async (task) => {
        await tasks.updateTaskStatus(task.id, !task.completed);
    };

    const handleDelete = async (id) => {
        await tasks.deleteTask(id);
    };

    const handleFilter = async () => {
        try {
            let completed = null;
            if (completedFilter === 'true') completed = true;
            else if (completedFilter === 'false') completed = false;

            await tasks.fetchFilteredTasks(completed, dueAtFilter);
        } catch (error) {
            console.error('Error fetching filtered tasks:', error);
        }
    };

    const resetFilter=()=>{
        dueAtFilter = '';
        completedFilter = '';
        handleFilter();
    }
</script>

<h2>Task List</h2>

<!-- Filtry -->
<div class="filters uk-margin-bottom">
    <label>
        Completed:
        <select  bind:value={completedFilter} class="uk-select">
            <option selected= "selected" value="">All</option>
            <option value="true">Done</option>
            <option value="false">Pending</option>
        </select>
    </label>

    <label>
        Due Date:
        <input type="datetime-local" bind:value={dueAtFilter} class="uk-input" />
    </label>

    <button class="uk-button uk-button-primary" on:click={handleFilter}>Apply Filters</button>
    <button class="uk-button uk-button-primary" on:click={resetFilter}>Reset</button>
</div>

<!-- Lista zadań -->
{#each $tasks as task (task.id)}
    <div class="uk-card {task.completed ? 'completed' : ''} uk-card-default uk-card-body uk-margin-small">
        {#if isEditing && editedTask.id === task.id}
            <h3 class="uk-card-title">Editing Task</h3>
            <input bind:value={editedTask.title} class="uk-input uk-margin-small" placeholder="Edit title" />
            <input bind:value={editedTask.description} class="uk-input uk-margin-small" placeholder="Edit description" />
            <input bind:value={editedTask.dueAt} type="datetime-local" class="uk-input uk-margin-small" placeholder="Edit Due Date" />
            <div class="uk-button-group">
                <button class="uk-button uk-button-primary" on:click={handleEdit}>Save</button>
                <button class="uk-button uk-button-default" on:click={() => (isEditing = false)}>Cancel</button>
            </div>
        {:else}
            <h3 class="uk-card-title">{task.title}</h3>
            <p>{task.description}</p>
            <p><strong>Due Date:</strong> {formatEuropeanDate(task.dueAt)}</p>
            <p><strong>Status:</strong> {task.completed ? 'Completed' : 'Incomplete'}</p>
            <div class="uk-button-group">
                <button class="uk-button uk-button-secondary" on:click={() => startEdit(task)}>Edit</button>
                <button class="uk-button uk-button-success" on:click={() => handleStatus(task)}>
                    {task.completed ? '✔ Completed' : '❌ Pending'}
                </button>
                <button class="uk-button uk-button-danger" on:click={() => handleDelete(task.id)}>Delete</button>
            </div>
        {/if}
    </div>
{/each}

<style>
    .completed {
        text-decoration: line-through;
        opacity: 40%;
    }

    .filters {
        display: flex;
        gap: 15px;
    }

    button {
        margin-left: 10px;
    }
</style>