import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms'
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { ArtworkComponent } from './artwork/artwork.component';
import { SurveyComponent } from './survey/survey.component';
import { TourComponent } from './tour/tour.component';
import { HomeComponent } from './home/home.component';
import { CollectingComponent } from './collecting/collecting.component';
import { FormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    AppComponent,
    ArtworkComponent,
    SurveyComponent,
    TourComponent,
    HomeComponent,
    CollectingComponent
  ],
  entryComponents: [],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})

export class AppModule { }
