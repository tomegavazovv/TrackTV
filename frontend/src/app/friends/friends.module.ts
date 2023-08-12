import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {FriendRequestsComponent} from "./friend-requests/friend-requests.component";
import {FriendsComponent} from "./friends/friends.component";
import {MatButtonModule} from "@angular/material/button";
import {MatIconModule} from "@angular/material/icon";
import {SearchUsersComponent} from "./search-users/search-users.component";
import {MatListModule} from "@angular/material/list";
import {ReactiveFormsModule} from "@angular/forms";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import { SuggestionsComponent } from './suggestions/suggestions.component';
import {MatTableModule} from "@angular/material/table";

@NgModule({
  declarations: [FriendRequestsComponent, FriendsComponent, SearchUsersComponent, SuggestionsComponent],
    imports: [
        MatTableModule,
        ReactiveFormsModule,
        CommonModule,
        MatButtonModule,
        MatIconModule,
        MatListModule,
        MatFormFieldModule,
        MatInputModule
    ]
})
export class FriendsModule { }
