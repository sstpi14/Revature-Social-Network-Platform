import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Profiled } from 'src/app/models/dprofile';
import { Full_Display } from 'src/app/models/fulldisplay';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-displayblock',
  templateUrl: './displayblock.component.html',
  styleUrls: ['./displayblock.component.css']
})
export class DisplayblockComponent implements OnInit {
  
  profile : Profiled = <Profiled>{};
  id : number = 0;
  user : string = "";


  display1 : Full_Display = <Full_Display>{}

  displays : Array<Full_Display> = [this.display1];
  constructor(private apiServ : ApiService, private route : ActivatedRoute) { }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.id = params['id'];
      this.user = params['user']
      
    })
    console.log(this.apiServ.profileid);
    console.log(this.apiServ.visitUser);
  }

  getOneProfileByUsername(){
    this.apiServ.getOneProfileByUsername1().subscribe(response => {     
      this.profile = response.data; 
      //this.profileId = response.data.profileId;     
      //this.apiServ.profileid = this.profileId; 
      this.apiServ.visitUser = this.profile;
    })
  }

  liked(){

  }

}
