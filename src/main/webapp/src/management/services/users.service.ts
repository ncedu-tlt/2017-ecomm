import {Injectable} from "@angular/core";
import {Headers, Http} from "@angular/http";
import "rxjs/add/operator/toPromise";
import UserModel from "../models/user.model";

declare var contextPath: string;

@Injectable()
export class UsersService {

    private usersUrl = `${contextPath}/rest/ecomm/users`;

    private headers = new Headers({ 'Content-Type': 'application/json' });

    constructor(private http: Http) {
        
    }

    getUsers(): Promise<UserModel[]> {
        return this.http.get(this.usersUrl)
            .toPromise()
            .then(response => response.json() as UserModel[])
            .catch(this.handleError);
    }

    getUser(id: number): Promise<UserModel> {
        const url = `${this.usersUrl}/${id}`;
        return this.http.get(url)
            .toPromise()
            .then(response => response.json() as UserModel)
            .catch(this.handleError);
    }

    private handleError(error: any): Promise<any> {
        console.error('An error occurred', error);
        return Promise.reject(error.message || error);
    }
}