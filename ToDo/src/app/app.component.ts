import {Component, OnInit} from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {Task} from "./task";
import {TaskService} from "./task.service";
import {catchError, tap, throwError} from "rxjs";
import {HttpErrorResponse} from "@angular/common/http";
import {DatePipe, NgClass, NgForOf, NgIf} from "@angular/common";
import {FormsModule, NgForm} from "@angular/forms";

@Component({
  selector: 'app-root',
    imports: [
        NgForOf,
        FormsModule,
        DatePipe,
        NgClass,
        NgIf
    ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {
  title = 'ToDo';

  public tasks: Task[] = [];
  public selectedTask : Task | undefined;

  constructor(private taskService: TaskService) {

  }

  ngOnInit(): void {
      this.getTasks();
    }
  public getTasks(): void {
    this.taskService.getTask().pipe(
        tap((response: Task[]) => this.tasks = response),
        catchError((error: HttpErrorResponse) => {
          alert(error.message);
          return throwError(() => error);
        })
    ).subscribe();
  }

    onAddTask(addForm:NgForm) {
        const closeButton = document.getElementById('add-task-form');
        if (closeButton) {
            closeButton.click();
        }
        this.taskService.addTask(addForm.value).pipe(

            tap((response: Task) => {
                console.log(response);
                this.getTasks();
                addForm.reset();
            }),
            catchError((error: HttpErrorResponse) => {
                alert(error.message);
                return throwError(() => error);
            })
        ).subscribe();
    }

    onUpdateTask(task: Task) {
        const closeButton = document.getElementById('edit-task-form');
        if (closeButton) {
            closeButton.click();
        }

        this.taskService.updateTask(task).pipe(
            tap((response: Task) => {
                console.log(response);
                this.getTasks();
            }),
            catchError((error: HttpErrorResponse) => {
                alert(error.message);
                return throwError(() => error);
            })
        ).subscribe();
    }

    onDeleteTask(taskId: number) {
        const closeButton = document.getElementById('delete-task-form');
        if (closeButton) {
            closeButton.click();
        }

        this.taskService.deleteTask(taskId).pipe(
            tap((response: void) => {
                console.log(response);
                this.getTasks();
            }),
            catchError((error: HttpErrorResponse) => {
                alert(error.message);
                return throwError(() => error);
            })
        ).subscribe();
    }

    onModalOpen(task: Task) {
        this.selectedTask = task;
    }

    onCompleteTask(taskId: number, completed: boolean) {
        this.taskService.completeTask(taskId, !completed).pipe(
            tap((response: void) => {
                console.log(response);
                this.getTasks();
            }),
            catchError((error: HttpErrorResponse) => {
                alert(error.message);
                return throwError(() => error);
            })
        ).subscribe();

    }
}
