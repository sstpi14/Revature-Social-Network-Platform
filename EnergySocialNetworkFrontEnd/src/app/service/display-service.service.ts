import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { JsonResponse } from '../models/jsonresponse';

@Injectable({
  providedIn: 'root'
})
export class DisplayServiceService {
  getOneDisplay() {
    throw new Error('Method not implemented.');
  }

  constructor(private httpCli:HttpClient) { }

  httpOptions = {
    headers: new HttpHeaders({
      "Content-Type":"application/json"
    })
  }

  getAllDisplays(){
    return this.httpCli.get<any>("http://localhost:9000/display",{
      withCredentials: true
    });
  }

  createDisplay(display:any): Observable<JsonResponse>{
    return this.httpCli.post<JsonResponse>("http://localhost:9000/display", JSON.stringify(display), this.httpOptions);
  }

  getAllDisplaysbyId(profileId:number){
    return this.httpCli.get<any>("http://localhost:9000/display/profile/"+profileId)
  }
}
