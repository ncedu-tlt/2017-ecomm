import {BrowserModule} from "@angular/platform-browser";
import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from "@angular/core";
import {FormsModule} from "@angular/forms";
import {HttpModule} from "@angular/http";
import {NgSemanticModule} from "ng-semantic";
import {TreeModule} from "angular-tree-component";
import {RoutingModule} from "./routing.module";
import {RootComponent} from "../components/root/root.component";
import {NavigationComponent} from "../components/navigation/navigation.component";
import {UsersListComponent} from "../components/users-list/users-list.component";
import {DataTableComponent} from "../components/data-table/data-table.component";
import {TopMenuComponent} from "../components/top-menu/top-menu.component";
import {CharGroupListComponent} from "../components/char-group-list/char-group-list.component";
import {UsersService} from "../services/users.service";
import {CharGroupService} from "../services/char-group.service";
import {CharGroupEditorComponent} from "../components/char-group-editor/char-group-editor.component";
import {UserEditorComponent} from "../components/user-editor/user-editor.component";
import {UserDetailsComponent} from "../components/user-details/user-details.component";
import {CharsListComponent} from "../components/chars-list/chars-list.component";
import {CategoriesTreeComponent} from "../components/categories-tree/categories-tree.component";
import {CharsListService} from "../services/charsList.service";
import {CategoryService} from "../services/category.service";
import {AsTreePipe} from "../pipes/asTree.pipe";
import {CategoryEditorComponent} from "../components/category-editor/category-editor.component";
import {UserReviewsComponent} from "../components/user-reviews/user-reviews.component";
import {UserOrdersComponent} from "../components/user-orders/user-orders.component";
import {ProductModelEditorComponent} from "../components/product-model-editor/product-model-editor.component";
import {CharEditorComponent} from "../components/char-editor/char-editor.component";

@NgModule({
    declarations: [
        RootComponent,
        NavigationComponent,
        UsersListComponent,
        DataTableComponent,
        UserEditorComponent,
        UserDetailsComponent,
        TopMenuComponent,
        CharGroupListComponent,
        CharGroupEditorComponent,
        CharsListComponent,
        CategoriesTreeComponent,
        AsTreePipe,
        CategoryEditorComponent,
        UserReviewsComponent,
        UserOrdersComponent,
        ProductModelEditorComponent,
        CharEditorComponent
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
        CharGroupService,
        CharsListService,
        CategoryService
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA],
    bootstrap: [RootComponent]
})
export class AppModule {
}
