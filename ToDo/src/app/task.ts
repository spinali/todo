export interface Task {
    id: number;
    title: string;
    description: string;
    dueAt : Date;
    createdAt: Date;
    updatedAt: Date;
    completed: boolean;
}
