import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {UsersListComponent} from "../components/users-list/users-list.component";
import {CharGroupListComponent} from "../components/char-group-list/char-group-list.component";
import {UserDetailsComponent} from "../components/user-details/user-details.component";
import {CharGroupEditorComponent} from "../components/char-group-editor/char-group-editor.component";
import {CharEditorComponent} from "../components/char-editor/char-editor.component";
import {CategoryEditorComponent} from "../components/category-editor/category-editor.component";
import {ProductModelEditorComponent} from "../components/product-model-editor/product-model-editor.component";

const ROUTES: Routes = [
    { path: '', redirectTo: '/users', pathMatch: 'full' },
    { path: 'users',  component: UsersListComponent },
    { path: 'user/:id', component: UserDetailsComponent},
    { path: 'char-group-list', component: CharGroupListComponent },
    { path: 'char-group-editor/:id', component: CharGroupEditorComponent },
    { path: 'char-editor', component: CharEditorComponent },
    { path: 'category-editor', component: CategoryEditorComponent },
    { path: 'product-model-editor', component: ProductModelEditorComponent },
    { path: '**', redirectTo: '/users' }
];

@NgModule({
    imports: [ RouterModule.forRoot(ROUTES, {useHash: true}) ],
    exports: [ RouterModule ]
})
export class RoutingModule {}
