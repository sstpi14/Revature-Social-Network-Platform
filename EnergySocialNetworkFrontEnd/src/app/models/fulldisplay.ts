import { Profiled } from "./dprofile";

export interface Full_Display{
    displayId: number;
    image: {
        image: string;
    }
    profile: Profiled;
    likers: number;
    description: string; 
}