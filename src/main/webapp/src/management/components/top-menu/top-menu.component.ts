import {Component} from '@angular/core';

declare const contextPath: string;

@Component({
    selector: 'nc-top-menu',
    templateUrl: 'top-menu.component.html',
    styleUrls: ['top-menu.component.css']
})
export class TopMenuComponent {
    urlProperties: string = contextPath + '/properties';
    urlCatalog: string = contextPath + '/catalog';
}