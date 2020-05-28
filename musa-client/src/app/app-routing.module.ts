import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ArtworkComponent } from './artwork/artwork.component';
import { SurveyComponent } from './survey/survey.component';
import { TourComponent } from './tour/tour.component';
import { HomeComponent } from './home/home.component';
import { CollectingComponent } from './collecting/collecting.component';

const routes: Routes = [
  {path: '', redirectTo: "/home", pathMatch: 'full'},
  {path:'home', component: HomeComponent},
  {path:'position', component: ArtworkComponent},
  {path:'survey', component: SurveyComponent},
  {path:'tour', component: TourComponent},
  {path:'collecting', component: CollectingComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
