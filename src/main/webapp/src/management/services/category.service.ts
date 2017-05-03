import {Injectable} from "@angular/core";
import {Http, RequestOptions, Headers} from "@angular/http";
import CategoryModel from "../models/category.model";

declare var contextPath: string;

@Injectable()
export class CategoryService {
    private categoryUrl = `${contextPath}/rest/ecomm/categories`;

    private headers = new Headers({'Content-Type': 'application/json'});

    options: RequestOptions = new RequestOptions({headers: this.headers});

    constructor(private http: Http) {
    }

    getAll(): Promise<CategoryModel[]> {
        return this.http.get(this.categoryUrl)
            .toPromise()
            .then(response => response.json() as CategoryModel[])
            .catch(this.handleError);
    }

    handleError(error: any): Promise<any> {
        console.error('An error occurred', error);
        return Promise.reject(error.message || error);
    }
}
