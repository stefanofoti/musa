import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  API_KEY = 'YOUR_API_KEY';
  constructor(private httpClient: HttpClient) { }

  public getPosition(){
    return this.httpClient.get(`https://localhost:5000/api/Musa/A`);
  }

}
