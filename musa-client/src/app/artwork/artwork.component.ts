import { Component, OnInit } from '@angular/core';
import { ApiService } from '../api.service';
import { interval, Subscription } from 'rxjs';

@Component({
  selector: 'app-artwork',
  templateUrl: './artwork.component.html',
  styleUrls: ['./artwork.component.css']
})
export class ArtworkComponent implements OnInit {

  lastArtwork;
  positionSubscription: Subscription

  constructor(private apiService: ApiService){
    this.positionSubscription= interval(1000).subscribe((x =>{this.doStuff();}));
  }

  ngOnInit(): void {
  }

  doStuff(){
    this.apiService.getPosition().subscribe((data)=>{
      console.log(data);
      this.lastArtwork = data;
    );
  }

}
