import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DisplayServiceService {

  constructor(private httpCli:HttpClient) { }

  getAllDisplays(){
    return this.httpCli.get<any>("http://localhost:9000/display",{
      withCredentials: true
    });
  }
}
