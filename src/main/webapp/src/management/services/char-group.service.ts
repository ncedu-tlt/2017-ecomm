import {Injectable} from '@angular/core';
import {Http, Headers, RequestOptions, Response} from "@angular/http";
import "rxjs/add/operator/toPromise";
import CharGroupModel from "../models/char-group.model";
import {Observable} from "rxjs";

declare var contextPath: string;

@Injectable()
export class CharGroupService {

    private charGroupUrl = `${contextPath}/rest/ecomm/characteristicgroup`;

    private headers = new Headers({'Content-Type': 'application/json'});

    options: RequestOptions;

    constructor(private http: Http) {
        this.options = new RequestOptions({headers: this.headers});
    }

    getCharacteristicGroups(): Promise<CharGroupModel[]> {
        return this.http.get(this.charGroupUrl)
            .toPromise()
            .then(response => response.json() as CharGroupModel[])
            .catch(this.handleError);
    }

    addCharacteristicGroup(charGroupName: string, charGroupIdMax: number): Observable<CharGroupModel> {
        const groupId = ++charGroupIdMax;
        const url = `${this.charGroupUrl}`;
        const options = new RequestOptions({ headers: this.headers });
        return this.http.post(url, JSON.stringify
        ({
            characteristicGroupName: charGroupName,
            characteristicGroupId: groupId
        }), options)
            .map((res:Response) => res.json())
            .catch((error:any) => Observable.throw(error.json().error || 'Server error'));
    }

    deleteCharacteristicGroup(charGroupId: number): Promise<void> {
        const url = `${this.charGroupUrl}/${charGroupId}`;
        return this.http.delete(url, {headers: this.headers})
            .toPromise()
            .then(() => null)
            .catch(this.handleError);
    }

    handleError(error: any): Promise<any> {
        console.error('An error occurred', error);
        return Promise.reject(error.message || error);
    }

}
