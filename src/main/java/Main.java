
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {

        // Memanggil foto background
        Image gambar = new Image(
                getClass().getResourceAsStream("/images/Background.jpeg")
        );

        ImageView background = new ImageView(gambar);
        background.setFitWidth(900);
        background.setFitHeight(600);
        background.setPreserveRatio(false);

        // Judul game
        Label judul = new Label("RODA KATA");
        judul.setFont(Font.font("Arial", FontWeight.BOLD, 48));
        judul.setTextFill(Color.BLACK);

        Label subjudul = new Label("BY FADHIL MUHAMMAD HAFIZH");
        subjudul.setTextFill(Color.BLACK);
        subjudul.setFont(Font.font("Arial", 16));

        // Tombol mulai
        Button mulai = new Button("MULAI BERMAIN");
        mulai.setPrefSize(230, 50);

        // Tombol cara bermain
        Button cara = new Button("CARA BERMAIN");
        cara.setPrefSize(230, 50);

        // Tombol keluar
        Button keluar = new Button("KELUAR");
        keluar.setPrefSize(230, 45);


// Mengatur warna tombol

        mulai.setStyle(
                "-fx-background-color: #1565C0;" +
                        "-fx-text-fill: #FFFFFF;" +
                        "-fx-font-size: 16px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 12;"
        );

        cara.setStyle(
                "-fx-background-color: #1976D2;" +
                        "-fx-text-fill: #FFFFFF;" +
                        "-fx-font-size: 15px;" +
                        "-fx-background-radius: 12;"
        );

        keluar.setStyle(
                "-fx-background-color: #42A5F5;" +
                        "-fx-text-fill: #FFFFFF;" +
                        "-fx-font-size: 14px;" +
                        "-fx-background-radius: 12;"
        );


        // Mengatur aksi tombol
        mulai.setOnAction(e -> tampilkanPermainan(stage));

        cara.setOnAction(e ->
                System.out.println("Cara bermain")
        );

        keluar.setOnAction(e -> stage.close());

        // Mengatur posisi menu
        VBox menu = new VBox(
                20, judul, subjudul, mulai, cara, keluar
        );

        menu.setAlignment(Pos.CENTER);

        // Menggabungkan background dan menu
        StackPane root = new StackPane();
        root.getChildren().addAll(background, menu);

        Scene scene = new Scene(root, 900, 600);

        stage.setTitle("Game Roda Kata");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void tampilkanPermainan(Stage stage) {

        Label judul = new Label("RODA KATA");
        judul.setFont(Font.font("Arial", FontWeight.BOLD, 35));

        Label level = new Label("LEVEL 1");
        Label koin = new Label("KOIN: 50");
        int[] jumlahKoin = {50};
        // Menandai apakah level sudah selesai
        boolean[] levelSelesai = {false};

        // Menyimpan status kata bonus
        boolean[] bonusDitemukan = {false, false};
        // Menghitung jumlah petunjuk yang digunakan
        int[] jumlahPetunjuk = {0};
        // Grid teka-teki silang
        javafx.scene.layout.GridPane grid =
                new javafx.scene.layout.GridPane();

        grid.setAlignment(Pos.CENTER);
        grid.setHgap(4);
        grid.setVgap(4);

        // Menyiapkan enam kotak jawaban JEPANG
        Label[] kotakJawaban = new Label[6];

        for (int i = 0; i < 6; i++) {

            Label kotak = new Label("");

            kotak.setPrefSize(45, 45);
            kotak.setAlignment(Pos.CENTER);

            kotak.setStyle(
                    "-fx-background-color: #DCE6F1;" +
                            "-fx-border-color: #1565C0;" +
                            "-fx-border-width: 1;" +
                            "-fx-font-size: 18px;" +
                            "-fx-font-weight: bold;"
            );

            kotakJawaban[i] = kotak;
            grid.add(kotak, i, 0);
        }

        // Menyimpan huruf yang dipilih
        StringBuilder jawaban = new StringBuilder();

        Label tampilanJawaban = new Label("");
        tampilanJawaban.setFont(
                Font.font("Arial", FontWeight.BOLD, 24)
        );

        Label pesan = new Label("");
        // Informasi penyelesaian level
        Label statusLevel = new Label("");
        statusLevel.setFont(
                Font.font("Arial", FontWeight.BOLD, 20)
        );

        // Tombol level berikutnya
        Button levelBerikutnya = new Button("LEVEL BERIKUTNYA");
        levelBerikutnya.setPrefSize(200, 45);
        levelBerikutnya.setVisible(false);
        levelBerikutnya.setManaged(false);
        pesan.setFont(Font.font("Arial", 15));

        // Membuat roda huruf
        javafx.scene.layout.HBox rodaHuruf =
                new javafx.scene.layout.HBox(12);

        rodaHuruf.setAlignment(Pos.CENTER);

        String[] huruf = {"J", "E", "P", "A", "N", "G"};
        // Mengacak huruf saat permainan pertama dimulai
        for (int i = huruf.length - 1; i > 0; i--) {

            int j = (int) (Math.random() * (i + 1));

            String sementara = huruf[i];
            huruf[i] = huruf[j];
            huruf[j] = sementara;
        }

        for (String h : huruf) {

            Button tombolHuruf = new Button(h);

            tombolHuruf.setPrefSize(55, 55);

            tombolHuruf.setStyle(
                    "-fx-background-color: #1565C0;" +
                            "-fx-text-fill: white;" +
                            "-fx-font-size: 20px;" +
                            "-fx-font-weight: bold;" +
                            "-fx-background-radius: 50;"
            );

            // Aksi ketika huruf diklik
            tombolHuruf.setOnAction(e -> {

                jawaban.append(h);

                tampilanJawaban.setText(
                        jawaban.toString()
                );

                pesan.setText("");
            });

            // Memasukkan tombol ke roda huruf
            rodaHuruf.getChildren().add(tombolHuruf);
        }

// Tombol ACAK (di luar perulangan)
        Button acak = new Button("ACAK");
        acak.setPrefSize(110, 40);

        acak.setOnAction(e -> {

            // Mengacak posisi huruf
            for (int i = huruf.length - 1; i > 0; i--) {

                int j = (int) (Math.random() * (i + 1));

                String sementara = huruf[i];
                huruf[i] = huruf[j];
                huruf[j] = sementara;
            }

            // Menghapus tombol sebelumnya
            rodaHuruf.getChildren().clear();

            // Membuat ulang tombol huruf
            for (String hurufBaru : huruf) {

                Button tombolBaru = new Button(hurufBaru);

                tombolBaru.setPrefSize(55, 55);

                tombolBaru.setStyle(
                        "-fx-background-color: #1565C0;" +
                                "-fx-text-fill: white;" +
                                "-fx-font-size: 20px;" +
                                "-fx-font-weight: bold;" +
                                "-fx-background-radius: 50;"
                );

                tombolBaru.setOnAction(event -> {

                    jawaban.append(hurufBaru);

                    tampilanJawaban.setText(
                            jawaban.toString()
                    );

                    pesan.setText("");
                });

                rodaHuruf.getChildren().add(tombolBaru);
            }
        });





            // Tombol HAPUS
            Button hapus = new Button("HAPUS");
            hapus.setOnAction(e -> {

                if (jawaban.length() > 0) {

                    jawaban.deleteCharAt(
                            jawaban.length() - 1
                    );

                    tampilanJawaban.setText(
                            jawaban.toString()
                    );
                }

                pesan.setText("");
            });

            // Tombol KIRIM
            // Tombol KIRIM
            Button kirim = new Button("KIRIM");
            kirim.setPrefSize(110, 40);

            kirim.setOnAction(e -> {

                // Isi pemeriksaan jawabanmu tetap di sini

            });

// Tombol PETUNJUK
            Button petunjuk = new Button("PETUNJUK");

            petunjuk.setPrefSize(110, 40);

            petunjuk.setOnAction(e -> {

                if (jumlahKoin[0] < 10) {

                    pesan.setText("Koin tidak cukup!");

                } else if (jumlahPetunjuk[0] >= 6) {

                    pesan.setText("Semua huruf sudah terbuka!");

                } else {

                    // Mengurangi koin
                    jumlahKoin[0] -= 10;
                    koin.setText("KOIN: " + jumlahKoin[0]);

                    // Membuka satu huruf
                    int posisi = jumlahPetunjuk[0];

                    kotakJawaban[posisi].setText(
                            "JEPANG".substring(posisi, posisi + 1)
                    );

                    kotakJawaban[posisi].setStyle(
                            "-fx-background-color: #FFE082;" +
                                    "-fx-border-color: #F9A825;" +
                                    "-fx-font-size: 18px;" +
                                    "-fx-font-weight: bold;"
                    );

                    jumlahPetunjuk[0]++;

                    pesan.setText("Petunjuk berhasil digunakan! -10 koin");
                }
            });


            kirim.setOnAction(e -> {

                String kata = jawaban.toString();

                if (kata.length() < 2) {

                    pesan.setText("Masukkan minimal 2 huruf!");

                } else if (kata.equals("JEPANG")) {

                    // Mengisi grid jika jawaban benar
                    for (int i = 0; i < 6; i++) {
                        kotakJawaban[i].setText(
                                String.valueOf(kata.charAt(i))
                        );

                        kotakJawaban[i].setStyle(
                                "-fx-background-color: #A5D6A7;" +
                                        "-fx-border-color: #2E7D32;" +
                                        "-fx-font-size: 18px;" +
                                        "-fx-font-weight: bold;"
                        );
                    }

                    pesan.setText("BENAR! JEPANG DITEMUKAN!");
                    // Memeriksa penyelesaian level
                    if (!levelSelesai[0]) {

                        levelSelesai[0] = true;

                        // Hadiah penyelesaian level
                        jumlahKoin[0] += 20;
                        koin.setText("KOIN: " + jumlahKoin[0]);

                        statusLevel.setText("LEVEL SELESAI!");

                        levelBerikutnya.setVisible(true);
                        levelBerikutnya.setManaged(true);
                    }

                    jawaban.setLength(0);
                    tampilanJawaban.setText("");

                } else if (kata.equals("PENA")) {

                    if (!bonusDitemukan[0]) {

                        bonusDitemukan[0] = true;
                        jumlahKoin[0] += 5;

                        koin.setText("KOIN: " + jumlahKoin[0]);
                        pesan.setText("KATA BONUS PENA! +5 KOIN");

                    } else {
                        pesan.setText("Kata PENA sudah ditemukan!");
                    }

                    jawaban.setLength(0);
                    tampilanJawaban.setText("");

                } else if (kata.equals("GENAP")) {

                    if (!bonusDitemukan[1]) {

                        bonusDitemukan[1] = true;
                        jumlahKoin[0] += 5;

                        koin.setText("KOIN: " + jumlahKoin[0]);
                        pesan.setText("KATA BONUS GENAP! +5 KOIN");

                    } else {
                        pesan.setText("Kata GENAP sudah ditemukan!");
                    }

                    jawaban.setLength(0);
                    tampilanJawaban.setText("");

                } else {
                    pesan.setText("Jawaban belum tepat!");
                }
            });


            // Mengatur tombol
            hapus.setPrefSize(110, 40);
            kirim.setPrefSize(110, 40);

        javafx.scene.layout.HBox tombolAksi =
                new javafx.scene.layout.HBox(15, acak, hapus, kirim, petunjuk);

            tombolAksi.setAlignment(Pos.CENTER);

            // Tombol kembali
            Button kembali = new Button("KEMBALI");
            kembali.setOnAction(e -> start(stage));
            // Aksi tombol LEVEL BERIKUTNYA
        levelBerikutnya.setOnAction(e -> {

            pesan.setText("LEVEL 2 SEDANG DISIAPKAN!");

        });

            // Menggabungkan semua tampilan
            VBox tampilan = new VBox(
                    18,
                    judul,
                    level,
                    koin,
                    statusLevel,
                    grid,
                    tampilanJawaban,
                    rodaHuruf,
                    tombolAksi,
                    pesan,
                    levelBerikutnya,
                    kembali
            );

            tampilan.setAlignment(Pos.CENTER);
            tampilan.setStyle("-fx-background-color: #F4F7FB;");

            Scene scene = new Scene(tampilan, 900, 600);

            stage.setScene(scene);

        } // Penutup method tampilkanPermainan

        public static void main (String[]args){
            launch(args);
        }
    }
