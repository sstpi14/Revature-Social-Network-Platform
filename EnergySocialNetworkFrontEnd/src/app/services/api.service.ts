import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, retry} from 'rxjs/operators';
import { JsonResponse } from '../models/jsonresponse';
import { Profile } from '../models/profile'

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  baseurl = "http://localhost:9000/";


  constructor(private httpCli : HttpClient) { }

  httpOptions = {
    headers: new HttpHeaders({
      "Content-Type":"application/json"
    })
  }

  //profile API


  getAllProfiles() {
    return this.httpCli.get<any>(this.baseurl + "profile/all")
  }

  getOneProfileByProfileId(profileId : number) {
    return this.httpCli.get<Profile>(this.baseurl + "profile/login/" + profileId)
  }

  getOneProfileByUsername(username : string): Observable<JsonResponse>{
    return this.httpCli.get<JsonResponse>(this.baseurl + "profile/username/" + username)
  }

  createProfile(profile: Profile): Observable<JsonResponse> {
    return this.httpCli.post<JsonResponse>(this.baseurl + "profile", JSON.stringify(profile), this.httpOptions)
  }

  //session API

  loginSession(profile: Profile): Observable<String> {
    return this.httpCli.post<String>(this.baseurl + "session", JSON.stringify(profile), this.httpOptions)
  }

}
