import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {CreateConcertComponent} from './concerts/create-concert/create-concert.component';
import {ListConcertsComponent} from './concerts/list-concerts/list-concerts.component';

const routes: Routes = [
  {
    path: 'concert', children: [
      {path: 'create', component: CreateConcertComponent },
      {path: 'list', component: ListConcertsComponent }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true, relativeLinkResolution: 'legacy' }),],
  exports: [RouterModule]
})
export class AppRoutingModule { }
