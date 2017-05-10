import {Component} from '@angular/core';

declare const contextPath: string;

@Component({
    selector: 'nc-top-menu',
    templateUrl: 'top-menu.component.html'
})
export class TopMenuComponent {
    url: string = contextPath + '/properties';
}