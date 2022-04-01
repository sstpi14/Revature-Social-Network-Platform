package com.revature.RevatureSocialNetwork.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class JsonResponse {
    Boolean success;
    String message;
    Object data;
}
