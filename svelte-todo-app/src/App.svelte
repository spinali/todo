
<script>

	import 'uikit/dist/css/uikit.min.css';
	import UIkit from 'uikit';
	import Icons from 'uikit/dist/js/uikit-icons';

	UIkit.use(Icons);

	import TaskForm from './components/TaskForm.svelte';
	import TaskList from './components/TaskList.svelte';
	import {onMount} from 'svelte';
	import {tasks} from './store/taskStore.js';

	onMount(() => {
		tasks.fetchTasks();
	});
</script>

<main>

	<h1>Task Management App</h1>
	<TaskForm/>
	<TaskList/>
</main>

<style>
	h1 {
		text-align: center;
		color: #333;
	}
</style>