import {BrowserModule} from "@angular/platform-browser";
import {NgModule} from "@angular/core";
import {FormsModule} from "@angular/forms";
import {HttpModule} from "@angular/http";
import {RootComponent} from "../components/root/root.component";
import {NavigationComponent} from "../components/navigation/navigation.component";
import {UsersListComponent} from "../components/users-list/users-list.component";
import {DataTableComponent} from "../components/data-table/data-table.component";
import {UserDetailsComponent} from "../components/user-details/user-details.component";
import {UsersService} from "../services/users.service";
import {RoutingModule} from "./routing.module";

@NgModule({
    declarations: [
        RootComponent,
        NavigationComponent,
        UsersListComponent,
        DataTableComponent,
        UserDetailsComponent
    ],
    imports: [
        BrowserModule,
        FormsModule,
        HttpModule,
        RoutingModule
    ],
    providers: [
        UsersService
    ],
    bootstrap: [RootComponent]
})
export class AppModule {
}
