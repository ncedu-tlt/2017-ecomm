import {BrowserModule} from "@angular/platform-browser";
import {NgModule} from "@angular/core";
import {FormsModule} from "@angular/forms";
import {HttpModule} from "@angular/http";
import {NgSemanticModule} from "ng-semantic";
import {TreeModule} from "angular-tree-component";

import {RoutingModule} from "./routing.module";

import {RootComponent} from "../components/root/root.component";
import {NavigationComponent} from "../components/navigation/navigation.component";
import {UsersListComponent} from "../components/users-list/users-list.component";
import {DataTableComponent} from "../components/data-table/data-table.component";
import {UserDetailsComponent} from "../components/user-details/user-details.component";
import {TopMenuComponent} from "../components/top-menu/top-menu.component";
import {CharGroupListComponent} from "../components/char-group-list/char-group-list.component";

import {UsersService} from "../services/users.service";
import {CharGroupService} from "../services/char-group.service";
import {CharGroupEditorComponent} from "../components/char-group-editor/char-group-editor.component";

@NgModule({
    declarations: [
        RootComponent,
        NavigationComponent,
        UsersListComponent,
        DataTableComponent,
        UserDetailsComponent,
        TopMenuComponent,
        CharGroupListComponent,
        CharGroupEditorComponent,
    ],
    imports: [
        BrowserModule,
        FormsModule,
        HttpModule,
        RoutingModule,
        NgSemanticModule,
        TreeModule
    ],
    providers: [
        UsersService,
        CharGroupService
    ],
    bootstrap: [RootComponent]
})
export class AppModule {
}
