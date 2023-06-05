package com.example.nequifirebase.model;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class DatabaseHelper {
    private DatabaseReference databaseReference,databaseReferenceTransfer,databaseReferenceCard;

    public DatabaseHelper() {
        // Obtiene una instancia de la base de datos de Firebase
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        // Obtén una referencia a la ubicación de los usuarios en la base de datos
        databaseReference = firebaseDatabase.getReference("users");
        databaseReferenceTransfer = firebaseDatabase.getReference("tranferencias");
        databaseReferenceCard = firebaseDatabase.getReference("tarjetas");
    }

    ///////////// AGREGAR REGISTROS A LA BD FIREBASE //////////////////////////

    public void addUser(User user) {
        databaseReference.push().setValue(user);
    }
    public void addTrans(Transfer transfer, final DataCallback<Boolean> callback) {
        databaseReferenceTransfer.push().setValue(transfer)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        callback.onDataLoaded(true);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        callback.onDataLoaded(false);
                    }
                });
    }

    public void addCard(Card card, final DataCallback<Boolean> callback) {
        databaseReferenceCard.push().setValue(card)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        callback.onDataLoaded(true);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        callback.onDataLoaded(false);
                    }
                });
    }

    /////////////////// FIN AGREGAR REGISTROS EN FIREBASE /////////////////////////



    ///////////EDITAR REGISTROS EN FIREBASE//////////////////////////////////////

    public void updateBalance(String telefono, String newBalance, final DataCallback<Boolean> callback) {
        databaseReference.orderByChild("telefono").equalTo(telefono).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        String userId = snapshot.getKey();
                        DatabaseReference userRef = getUserRef(userId);
                        HashMap<String, Object> updateData = new HashMap<>();
                        updateData.put("saldo", newBalance);

                        userRef.updateChildren(updateData, new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                                if (databaseError == null) {
                                    callback.onDataLoaded(true);
                                } else {
                                    callback.onDataLoaded(false);
                                }
                            }
                        });
                    }
                } else {
                    callback.onDataLoaded(false);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                callback.onDataLoaded(false);
            }
        });
    }


    public void checkUserExists(String email, final DataCallback<Boolean> callback) {
        // Realiza una consulta en la base de datos para verificar si existe un usuario con el correo electrónico dado
        databaseReference.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean exists = dataSnapshot.exists();
                callback.onDataLoaded(exists);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                callback.onDataError(databaseError.getMessage());
            }
        });
    }

    //Revisar si existe el numero de Telefono en la BD para que ingrese a escribir su PIN
    public void checkPhoneExists(String telefono, final DataCallback<Boolean> callback) {
        // Realiza una consulta en la base de datos para verificar si existe un usuario con el correo electrónico dado
        databaseReference.orderByChild("telefono").equalTo(telefono).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean exists = dataSnapshot.exists();
                callback.onDataLoaded(exists);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                callback.onDataError(databaseError.getMessage());
            }
        });
    }

    public void checkCredentials(String telefono, String pin, final DataCallback<Boolean> callback) {
        // Realiza una consulta en la base de datos para verificar si hay un usuario con el correo electrónico y la contraseña dados
        databaseReference.orderByChild("telefono").equalTo(telefono).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean isValid = false;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    User user = snapshot.getValue(User.class);
                    if (user != null && user.getPin().equals(pin)) {
                        isValid = true;
                        break;
                    }
                }
                callback.onDataLoaded(isValid);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                callback.onDataError(databaseError.getMessage());
            }
        });
    }


    //TRAER DATOS PARA LA PANTALLA PRINCIPAL----------------------
    public void getUserData(String telefono,final DataCallback<User> callback) {
        databaseReference.orderByChild("telefono").equalTo(telefono).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = null;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    user = snapshot.getValue(User.class);
                    if (user != null) {
                        // Se encontró un usuario
                        break;
                    }
                }
                callback.onDataLoaded(user);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                callback.onDataError(databaseError.getMessage());
            }
        });
    }

    public DatabaseReference getUserRef(String userId) {
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("users").child(userId);
        return userRef;
    }

    public void getBalance(String telefono, final DataCallback<Double> callback) {
        databaseReference.orderByChild("telefono").equalTo(telefono).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        User user = snapshot.getValue(User.class);
                        if (user != null) {
                            Double balance = Double.parseDouble(user.getSaldo());
                            callback.onDataLoaded(balance);
                        }
                    }
                } else {
                    callback.onDataError("El usuario no existe");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                callback.onDataError("Error al obtener el saldo");
            }
        });
    }
///////////////////////////////////----------------------------------------


    // Interfaz para manejar los callbacks de datos
    public interface DataCallback<T> {
        void onDataLoaded(T data);
        void onDataError(String errorMessage);
    }



}