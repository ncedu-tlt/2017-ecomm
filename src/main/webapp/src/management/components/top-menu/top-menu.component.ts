import { Component } from '@angular/core';
import Item from "../../models/item";

@Component({
    selector: 'nc-top-menu',
    templateUrl: 'top-menu.component.html'
})
export class TopMenuComponent{
    items: Item[] = [
        {title: 'Product', icon: 'list layout icon', route: '/'},
        {title: 'Catalog', icon: 'book icon', route: '/'},
        {title: 'Users', icon: 'users icon', route: '/'},
        {title: 'Orders', icon: 'dollar icon', route: '/'},
        {title: 'Properties', icon: 'options icon', route: '/'}
    ];
}