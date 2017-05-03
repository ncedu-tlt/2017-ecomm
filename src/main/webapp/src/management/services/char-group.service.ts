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

    options: RequestOptions = new RequestOptions({headers: this.headers});

    constructor(private http: Http) {
    }

    getCharacteristicGroups(): Promise<CharGroupModel[]> {
        return this.http.get(this.charGroupUrl)
            .toPromise()
            .then(response => response.json() as CharGroupModel[])
            .catch(this.handleError);
    }

    getCharacteristicGroup(id: number): Promise<CharGroupModel>{
        const url = `${this.charGroupUrl}/${id}`;
        return this.http.get(url)
            .toPromise()
            .then(response => response.json() as CharGroupModel)
            .catch(this.handleError);
    }

    addCharacteristicGroup(charGroupName: string): Promise<CharGroupModel> {
        let bodyRequest: any = {characteristicGroupName: charGroupName};
        return this.http.post(this.charGroupUrl, bodyRequest, this.options)
            .toPromise()
            .then(response => response.json() as CharGroupModel)
            .catch(this.handleError)
    }

    updateCharacteristicGroup(charGroup: CharGroupModel): Promise<CharGroupModel> {
        return this.http.put(this.charGroupUrl, charGroup, this.options)
            .toPromise()
            .then(response => response.json() as CharGroupModel)
            .catch(this.handleError)
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
