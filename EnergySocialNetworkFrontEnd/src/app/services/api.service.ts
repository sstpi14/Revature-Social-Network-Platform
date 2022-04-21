import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, retry} from 'rxjs/operators';
import { Profiled } from '../models/dprofile';
import { Full_Display } from '../models/fulldisplay';
import { JsonResponse } from '../models/jsonresponse';
import { Profile } from '../models/profile'

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  displayid : number = 0;
  profileid : number = 0;
  username : string = "";
  currentUser : any= {};
  visitUser : Profiled = <Profiled>{};
  display : Full_Display = <Full_Display>{};


  baseurl = "http://localhost:9000/";

  httpOptions = {
    headers: new HttpHeaders({
      "Content-Type":"application/json"
    })
  }

  constructor(private httpCli : HttpClient) { }

  //Display Api
  getAllGivenProfileId(profileId : number){
    return this.httpCli.get<JsonResponse>(`${this.baseurl}display/profile/${profileId}`)
  }

  getOneDisplay(displayId : number){
    return this.httpCli.get<JsonResponse>(`${this.baseurl}display/display/${displayId}`)
  }
  
  //profile API
  getAllProfiles(){
    return this.httpCli.get<any>(this.baseurl + "profile/all")
  }

  getOneProfileByProfileId(profileId : number) {
    return this.httpCli.get<JsonResponse>(this.baseurl + "profile/login/" + profileId)
  }

  getOneProfileByUsername(username : string): Observable<JsonResponse>{
    return this.httpCli.get<JsonResponse>(this.baseurl + "profile/username/" + username)
  }
  getOneProfileByUsername1(): Observable<JsonResponse>{
    return this.httpCli.get<JsonResponse>(this.baseurl + "profile/username/" + this.username)
  }

  createProfile(profile: Profile): Observable<JsonResponse> {
    return this.httpCli.post<JsonResponse>(this.baseurl + "profile", JSON.stringify(profile), this.httpOptions)
  }

  //session API

  loginSession(profile: Profile): Observable<String> {
    return this.httpCli.post<String>(this.baseurl + "session", JSON.stringify(profile), this.httpOptions)
  }

  //Liker API

  addLikeOrDislike(displayId:number,profileId:number,display:any){
    return this.httpCli.patch<JsonResponse>(this.baseurl+"display/"+displayId+"/profile/"+profileId,display);
  }

  getAllLikersOnDisplay(displayId:number):Observable<JsonResponse>{
    return this.httpCli.get<JsonResponse>(this.baseurl+"likers/"+displayId);
  }
}
