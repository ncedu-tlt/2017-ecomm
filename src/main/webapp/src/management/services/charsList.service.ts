import {Injectable} from "@angular/core";
import {RequestOptions, Headers, Http} from "@angular/http";
import CharacteristicListModel from "../models/charsListModel";

declare var contextPath: string;

@Injectable()
export class CharsListService {

    private characteristicUrlRest1 = `${contextPath}/rest/ecomm/characteristic`;
    private characteristicUrlRest2 = `${contextPath}/rest/ecomm/v2/characteristics`;

    private headers = new Headers({'Content-Type': 'application/json'});

    options: RequestOptions = new RequestOptions({headers: this.headers});

    constructor(private http: Http) {
    }

    getByCategoryId(categoryId: number): Promise<CharacteristicListModel[]>{
        const url = `${this.characteristicUrlRest2}/${categoryId}`;
        return this.http.get(url)
            .toPromise()
            .then(response => response.json() as CharacteristicListModel[])
            .catch(this.handleError);
    }

    delete(characteristicId: number): Promise<void>{
        const url = `${this.characteristicUrlRest1}/${characteristicId}`;
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
