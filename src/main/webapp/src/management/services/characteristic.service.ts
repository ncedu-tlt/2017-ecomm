import {Injectable} from "@angular/core";
import {RequestOptions, Headers, Http} from "@angular/http";
import CharacteristicModel from "../models/characteristic.model";

declare var contextPath: string;

@Injectable()
export class CharacteristicService {

    private characteristicUrl = `${contextPath}/rest/ecomm/characteristic`;

    private headers = new Headers({'Content-Type': 'application/json'});

    options: RequestOptions = new RequestOptions({headers: this.headers});

    constructor(private http: Http) {
    }

    getCharByCategoryAndGroup(categoryId: number, groupId: number): Promise<CharacteristicModel[]> {
        const url = `${this.characteristicUrl}/${categoryId}/${groupId}`;
        return this.http.get(url)
            .toPromise()
            .then(response => response.json() as CharacteristicModel[])
            .catch(this.handleError);
    }

    handleError(error: any): Promise<any> {
        console.error('An error occurred', error);
        return Promise.reject(error.message || error);
    }

}
