import { Injectable } from '@angular/core';
import {Http} from "@angular/http";
import "rxjs/add/operator/toPromise";
import CharGroupModel from "../models/char-group.model";

declare var contextPath: string;

@Injectable()
export class CharGroupService {

    private charGroupUrl = `${contextPath}/rest/ecomm/characteristicgroup`;

    private headers = new Headers({ 'Content-Type': 'application/json' });

    constructor(private http: Http) {}

    getCharacteristicGroups(): Promise<CharGroupModel[]>{
        return this.http.get(this.charGroupUrl)
            .toPromise()
            .then(response => response.json() as CharGroupModel[])
            .catch(this.handleError);
    }

    handleError(error: any): Promise<any>{
        console.error('An error occurred', error);
        return Promise.reject(error.message || error);
    }

}
