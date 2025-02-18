import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Task} from "./task";

@Injectable({
  providedIn: 'root'
})
export class TaskService {
  private url ="http://localhost:8081/api";

  constructor(private http: HttpClient) { }
  public getTask(): Observable<Task[]> {
    return this.http.get<Task[]>(`${this.url}/tasks`);
  }
  public addTask(task: Task): Observable<Task> {
    return this.http.post<Task>(`${this.url}/tasks`, task);
  }
  public updateTask(task: Task): Observable<Task> {
    return this.http.put<Task>(`${this.url}/tasks/${task.id}`, task);
  }
  public deleteTask(taskId: number): Observable<void> {
    return this.http.delete<void>(`${this.url}/tasks/${taskId}`);
  }
  public completeTask(taskId: number, completed: boolean): Observable<void> {
    return this.http.patch<void>(`${this.url}/tasks/${taskId}`, {completed});
  }
}
