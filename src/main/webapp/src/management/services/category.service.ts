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

    get(id: number): Promise<CategoryModel>{
        const url = `${this.categoryUrl}/${id}`;
        return this.http.get(url)
            .toPromise()
            .then(response => response.json() as CategoryModel)
            .catch(this.handleError);
    }

    add(categoryName: string): Promise<CategoryModel> {
        let bodyRequest: any = {name: categoryName};
        return this.http.post(this.categoryUrl, bodyRequest, this.options)
            .toPromise()
            .then(response => response.json() as CategoryModel)
            .catch(this.handleError)
    }

    update(category: CategoryModel): Promise<CategoryModel> {
        return this.http.put(this.categoryUrl, category, this.options)
            .toPromise()
            .then(response => response.json() as CategoryModel)
            .catch(this.handleError)
    }

    deleteCategory(categoryId: number): Promise<void> {
        const url = `${this.categoryUrl}/${categoryId}`;
        return this.http.delete(url, this.options)
            .toPromise()
            .then(() => null)
            .catch(this.handleError);
    }

    handleError(error: any): Promise<any> {
        console.error('An error occurred', error);
        return Promise.reject(error.message || error);
    }
}
