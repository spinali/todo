<script>
    import { tasks } from '../store/taskStore';
    let title = '';
    let description = '';
    let dueAt = '';

    const handleSubmit = async () => {
        if (!title.trim()) {
            alert('Title is required');
            return;
        }

        const newTask = { title, description, dueAt };
        await tasks.addTask(newTask);

        title = '';
        description = '';
        dueAt = '';
    };
</script>

<form on:submit|preventDefault={handleSubmit} class="uk-form-stacked uk-card uk-card-default uk-card-body">
    <div class="uk-margin">
        <label class="uk-form-label" for="title">Title</label>
        <input
                id="title"
                type="text"
                bind:value={title}
                placeholder="Title"
                class="uk-input"
                required
        />
    </div>

    <div class="uk-margin">
        <label class="uk-form-label" for="description">Description</label>
        <textarea
                id="description"
                bind:value={description}
                placeholder="Description"
                maxLength="255"
                class="uk-input"
        />
    </div>

    <div class="uk-margin">
        <label class="uk-form-label" for="dueAt">Due Date</label>
        <input
                id="dueAt"
                type="datetime-local"
                bind:value={dueAt}
                placeholder="Due Date"
                class="uk-input"
                required
        />
    </div>

    <button type="submit" class="uk-button uk-button-primary">Add Task</button>
</form>

<style>
    .uk-card {
        width: 400px;
        margin: 20px auto;
        padding: 20px;
        border-radius: 10px;
        box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
    }

    .uk-form-label {
        font-weight: bold;
    }

    .uk-button {
        width: 100%;
        padding: 10px;
    }
</style>