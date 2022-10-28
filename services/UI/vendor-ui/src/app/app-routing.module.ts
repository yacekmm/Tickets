import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {CreateConcertComponent} from './concerts/create-concert/create-concert.component';
import {ListConcertComponent} from './concerts/list-concert/list-concert.component';

const routes: Routes = [
  {
    path: 'concert', children: [
      {path: 'create', component: CreateConcertComponent },
      {path: 'list', component: ListConcertComponent }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
