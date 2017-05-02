import {Injectable} from "@angular/core";
import {Headers, Http, RequestOptions} from "@angular/http";
import "rxjs/add/operator/toPromise";
import UserModel from "../models/user.model";
import RoleModel from "../models/role.model";

declare const contextPath: string;

@Injectable()
export class UsersService {

    private usersUrl = `${contextPath}/rest/ecomm/v2/users`;

    private rolesUrl = `${contextPath}/rest/ecomm/roles`;

    private headers = new Headers({ 'Content-Type': 'application/json' });

    options: RequestOptions = new RequestOptions({headers: this.headers});

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

    addUser(user: UserModel): Promise<UserModel> {
        return this.http.post(this.usersUrl, user, this.options)
            .toPromise()
            .then(response => response.json() as UserModel)
            .catch(this.handleError)
    }

    updateUser(user: UserModel):Promise<UserModel>{
        return this.http.put(this.usersUrl, user, this.options).toPromise()
            .then(response => response.json() as UserModel)
            .catch(this.handleError)
    }

    private handleError(error: any): Promise<any> {
        console.error('An error occurred', error);
        return Promise.reject(error.message || error);
    }

    deleteUser(userId: number): Promise<void> {
        const url = `${this.usersUrl}/${userId}`;
        return this.http.delete(url, {headers: this.headers})
            .toPromise()
            .then(() => null)
            .catch(this.handleError);
    }

    getRoles(): Promise<RoleModel[]>{
        return this.http.get(this.rolesUrl)
            .toPromise()
            .then(response => response.json() as RoleModel[])
            .catch(this.handleError);
    }
}