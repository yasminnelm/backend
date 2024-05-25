//package com.example.backend.model.mapper;
//
//import com.example.backend.model.dto.CompteDTO;
//import com.example.backend.model.entity.Compte;
//import com.example.backend.model.enumeration.CompteType;
//
//public class CompteMapper {
//
//
//    public static CompteDTO toDto(Compte compte) {
//        if (compte == null) {
//            return null;
//        }
//
//        return new CompteDTO(
//                compte.getId(),
//                compte.getPlafond(),
//                compte.getType().toString(),
//                compte.getClient().getId()
//        );
//    }
//
//    // Convertir de CompteDTO à Compte
//    public static Compte toEntity(CompteDTO compteDTO) {
//        if (compteDTO == null) {
//            return null;
//        }
//
//        Compte compte = new Compte();
//        compte.setId(compteDTO.getId());
////        compte.setPlafond(compteDTO.getPlafond());
////        // You may need to handle converting the type String back to an Enum here
////        compte.setType(CompteType.valueOf(compteDTO.getType()));
////        // Assuming you have a separate method to fetch the client by ID
////        // You may need to change this logic based on your application design
////         compte.setClient(clientService.findById(compteDTO.getClientId()));
//        return compte;
//    }
//}

