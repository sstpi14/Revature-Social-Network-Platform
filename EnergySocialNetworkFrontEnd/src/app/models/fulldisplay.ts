import { Profiled } from "./dprofile";

export interface Full_Display{
    displayId: number;
    img: {
        image: string;
    }
    profile: Profiled;
    likers: Array<Profiled>;
    description: string; 
}