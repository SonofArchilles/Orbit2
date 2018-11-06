package com.jacobjacob.orbit;

import android.graphics.Bitmap;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView Image, Background;
    public boolean drawLines, collision, Canleavescreen, drawn, drawTrails, Symmetry, Menu, Reset, drawvel, Rotate, Drawacc, ranP, Move, moved, Zoom, selecPlanet, DDD, DDDC;

    public int r, removed, randomplanets, symmetry, seekstart, x2, y2, x3, y3, selectedPlanet, x1, y1, x720, xfinger2, yfinger2;
    public float xscreen, yscreen, Trailsize, mass, x, y, radius, time, dt, stars, G, updatespeed, symmetrydist, distSunx, distSuny, rotangle, angle, angle1, xmove, ymove, xmove1, ymove1, zoom, xactionmove, yactionmove, xactionmovef2, yactionmovef2;

    public ArrayList<Planet> Planets = new ArrayList<>();
    public ArrayList<Star> Stars = new ArrayList<>();
    public ArrayList<Integer> LinesPlanets = new ArrayList<>();
    public ArrayList<Object> Drawobjects = new ArrayList();
    public float div;
    public float Camerax, RotationX, movespeed;
    public float Cameray, RotationY;
    public float Cameraz = 400;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);


        Camerax = 0;
        Cameray = 0;
        Cameraz = 1500;

        Image = findViewById(R.id.image);
        Background = findViewById(R.id.Background);
        xscreen = size.x;
        yscreen = size.y;
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        div = (float) 10;
        DDD = false;
        DDDC = true;
        //als nächstes ein Meunü!!!
        Rotate = false;
        Move = false;
        Menu = true;
        zoom = 1;
        drawTrails = true;//trails behind the planets 1
        drawLines = true;//lines between planets 1
        collision = true;//between planets 1
        Canleavescreen = true;//planets

        //random Planets
        randomplanets = 8;

        //Symetry options
        Symmetry = false;
        symmetry = (int) 0;
        symmetrydist = 500;
        rotangle = 0; //rotates all planets when created
        rotangle = 180;

        updatespeed = (float) 0.1;

        x = xscreen / 2;
        y = yscreen / 2;
        //G = 4,7* Math.pow(10,-5);
        G = (float) 1;//Gravity

        if (xscreen < yscreen) {
            x720 = (int) xscreen / 20;
        }
        if (yscreen < xscreen) {
            x720 = (int) yscreen / 20;
        }
        movespeed = 60;
        CreateStars();

        ToggleButton menu = findViewById(R.id.menu);
        menu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Menu = true;
                } else {
                    Menu = false;
                }
                newMenu();
            }
        });
        ToggleButton DrawTrails = findViewById(R.id.drawtrails);
        DrawTrails.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    drawTrails = true;
                } else {
                    drawTrails = false;
                }
                newMenu();
            }
        });

        ToggleButton DrawLines = findViewById(R.id.drawlines);
        DrawLines.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    drawLines = true;
                } else {
                    drawLines = false;
                }
                newMenu();
            }
        });

        ToggleButton Collision = findViewById(R.id.collisions);
        Collision.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    collision = true;
                } else {
                    collision = false;
                }
                newMenu();
            }
        });

        ToggleButton symetry = findViewById(R.id.symmetry);
        symetry.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Symmetry = true;
                } else {
                    Symmetry = false;
                }
                newMenu();
                Reset1();
            }
        });
        ToggleButton acc = findViewById(R.id.accel);
        acc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Drawacc = true;
                } else {
                    Drawacc = false;
                }
                draw();
                newMenu();
            }
        });

        ToggleButton screenlimit = findViewById(R.id.screenlimit);
        screenlimit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Canleavescreen = true;
                } else {
                    Canleavescreen = false;
                }
                newMenu();
            }
        });
        ToggleButton Rotate1 = findViewById(R.id.Rot);
        Rotate1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Rotate = true;
                } else {
                    Rotate = false;
                }
                newMenu();
            }
        });
        ToggleButton mov = findViewById(R.id.move);
        mov.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Move = true;
                } else {
                    Move = false;
                }
                newMenu();
            }
        });
        ToggleButton zom = findViewById(R.id.zoom);
        zom.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Zoom = true;
                } else {
                    Zoom = false;
                }
                newMenu();
            }
        });
        //Move Camera
        ToggleButton lft = findViewById(R.id.left);
        lft.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Cameraz += movespeed * Math.sin(RotationY);
                Camerax += movespeed * Math.cos(RotationY);
                draw();
                //newMenu();
            }
        });
        ToggleButton rig = findViewById(R.id.right);
        rig.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Cameraz += -movespeed * Math.sin(RotationY);
                Camerax += -movespeed * Math.cos(RotationY);
                draw();
                //newMenu();
            }
        });
        ToggleButton top = findViewById(R.id.top);
        top.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Cameraz += -movespeed * Math.cos(RotationY); //100;//
                Camerax += movespeed * Math.sin(RotationY); //+ xmove
                draw();
                //newMenu();
            }
        });
        ToggleButton bottom = findViewById(R.id.bottom);
        bottom.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Cameraz += movespeed * Math.cos(RotationY);
                Camerax += -movespeed * Math.sin(RotationY);
                draw();
                //newMenu();
            }
        });
        ToggleButton reset = findViewById(R.id.reset);
        reset.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                xmove1 = 0;
                ymove1 = 0;
                xmove = 0;
                ymove = 0;
                Reset1();
                newMenu();
            }
        });
        final Button symcounter = (Button) findViewById(R.id.symcounter);
        symcounter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = (TextView) findViewById(R.id.text);
                if (Symmetry) {

                    symmetry += 1;
                }
                if (ranP & !Symmetry) {
                    randomplanets += 1;
                    if (randomplanets > 20) {
                        randomplanets = 20;
                    }
                }
                Reset1();
            }
        });
        final Button symcountermin = (Button) findViewById(R.id.symcountermin);
        symcountermin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = (TextView) findViewById(R.id.text);
                if (Symmetry) {

                    symmetry -= 1;
                    if (symmetry < 0) {
                        symmetry = 0;
                    }
                }
                if (ranP & !Symmetry) {
                    randomplanets -= 1;
                    if (randomplanets < 0) {
                        randomplanets = 0;
                    }
                }
                Reset1();
            }
        });

        ToggleButton Drawvel = findViewById(R.id.drawVel);
        Drawvel.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    DDD = true;
                    drawvel = true;
                } else {
                    DDD = false;
                    drawvel = false;
                }
                newMenu();
            }
        });

        ToggleButton ranp = findViewById(R.id.ranp);
        ranp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ranP = true;
                } else {
                    ranP = false;
                }
                newMenu();
            }
        });
        ToggleButton Selectp = findViewById(R.id.selcp);
        Selectp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    selecPlanet = true;
                } else {
                    selecPlanet = false;
                }
                selectplanet();
                newMenu();
            }
        });
        SeekBar seekBar = findViewById(R.id.seekangle);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //rotangle = progress - seekstart;
                rotangle = progress;
                draw();
                /*
                if ((Symmetry & symmetry > 0 || Rotate)) { // & false == true
                    //angle = 360 / symmetry + rotangle;
                    for (int i = 0; i < Planets.size(); i++) {
                        if (i != 0) {

                            symmetrydist = (float) Math.sqrt(Math.pow(Planets.get(0).x - Planets.get(i).x, 2) + Math.pow(Planets.get(0).y - Planets.get(i).y, 2));

                            float angle2 = (float) Math.toDegrees(Planets.get(i).angle1) + rotangle;

                            distSunx = (float) (symmetrydist * Math.cos(Math.toRadians(angle2))); //funktioniert
                            distSuny = (float) (symmetrydist * Math.sin(Math.toRadians(angle2))); //funktioniert

                            angle = (float) Math.atan2(Planets.get(i).vy1, Planets.get(i).vx1) + (float) Math.toRadians(rotangle); //+ (float) Math.toRadians(angle2);


                            float dist2 = (float) Math.sqrt(Math.pow(Planets.get(i).vx1, 2) + Math.pow(Planets.get(i).vy1, 2)); //funktioniert
                            Planets.get(i).vx = (float) Math.cos(angle) * dist2; //funktioniert
                            Planets.get(i).vy = (float) Math.sin(angle) * dist2; //funktioniert

                            Planets.get(i).x = Planets.get(0).x + distSunx;
                            Planets.get(i).y = Planets.get(0).y + distSuny;
                        }
                    }
                    draw();
                }
                */
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                seekstart = seekBar.getProgress();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                seekstart = seekBar.getProgress();
            }
        });
        SeekBar seekzom = findViewById(R.id.seekzoom);
        seekzom.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                zoom = (float) Math.sqrt(progress);
                drawBackground();
                draw();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                seekstart = seekBar.getProgress();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                seekstart = seekBar.getProgress();
            }
        });

        Create();
    }

    public void CreateStars() {
        for (int i = 0; i < Planets.size(); i++) {
            Planets.get(i).xscreen = xscreen;
            Planets.get(i).yscreen = yscreen;
            Planets.get(i).randomColor = true;
        }
        stars = 250;
        for (int i = 0; i < stars; i++) {
            Stars.add(new Star(1, 1, 1));
        }
        for (int i = 0; i < Stars.size(); i++) {
            int min = 0;
            Random r = new Random();
            Stars.get(i).x = r.nextInt((int) (3 * xscreen)) - xscreen;
            Stars.get(i).y = r.nextInt((int) (3 * yscreen)) - yscreen;
            Stars.get(i).z = r.nextInt(50) + 50;
            min = 1;
            int max = 6;
            Stars.get(i).radius = r.nextInt(max - min) + min;
            if (Stars.get(i).radius > 4) {
                Stars.get(i).n = (int) (6 / 4 * ((yscreen - Stars.get(i).y) * Stars.get(i).y) / (yscreen) * 2 / xscreen);
            }
            if (Stars.get(i).radius <= 4) {
                Stars.get(i).n = (int) (((yscreen - Stars.get(i).y) * Stars.get(i).y) / (yscreen) * 2 / xscreen);
            }
            Drawobjects.add(Stars.get(i));
        }
    }

    public void Create() {
        newMenu();
        if (!Symmetry) {

            collision = true;
            Planets.add(new Planet(xscreen / 2, yscreen / 2, 0, 0, 0, xscreen * xscreen, "Sun", (float) 0.00004, 255, 230, 140));

            float mass_Earth = Planets.get(0).mass / 333000;// = xscreen*xscreen/333000
            /*
            Planets.add(new Planet(xscreen / 2, yscreen / 3, 0, 30, 0, mass_Earth, "Mars", xscreen * 1 / 20, 200, 80, 40));
            float mass_Mars = Planets.get(0).mass / 333000 * 1 / 9;
            Planets.add(new Planet(xscreen / 3, yscreen * 7 / 8, 0, 20, 270, mass_Mars, "Earth", xscreen * 1 / 70, 40, 40, 230));
            float mass_Jupiter = Planets.get(0).mass / 333000 * 318;
            Planets.add(new Planet(xscreen * 6 / 7, yscreen * 3 / 4, 0, 25, (float) (Math.PI / 2), mass_Jupiter, "Jupiter", xscreen * 1 / 50, 211, 184, 155));
            float mass_Venus = Planets.get(0).mass / 333000 * (float) 0.82;
            Planets.add(new Planet(xscreen * 4 / 5, yscreen * 2 / 9, 0, 40, -40, mass_Venus, "Venus", xscreen * 1 / 110, 255, 189, 151));

            //Bei "Random"  sind die Werte die max, und min = 0
            for (int i = 0; i < randomplanets; i++) {
                Planets.add(new Planet(xscreen, yscreen, 0, 30, 360, 50, "Random", (int) x, (int) x, (int) x, (int) x));
            }
            */

            Planets.add(new Planet(900, 900, -500, 30, 0, mass_Earth, "Mars", xscreen * 1 / 20, 200, 180, 50));
            Planets.add(new Planet(100, 900, -500, 30, 0, mass_Earth, "Mars", xscreen * 1 / 20, 200, 180, 50));
            Planets.add(new Planet(100, 100, -500, 30, 0, mass_Earth, "Mars", xscreen * 1 / 20, 200, 180, 50));
            Planets.add(new Planet(900, 100, -500, 30, 0, mass_Earth, "Mars", xscreen * 1 / 20, 200, 180, 50));
            /*
             */
            Planets.add(new Planet(900, 900, -100, 30, 0, mass_Earth, "Mars", xscreen * 1 / 20, 100, 180, 50));
            Planets.add(new Planet(100, 900, -100, 30, 0, mass_Earth, "Mars", xscreen * 1 / 20, 100, 180, 50));
            Planets.add(new Planet(900, 100, -100, 30, 0, mass_Earth, "Mars", xscreen * 1 / 20, 100, 180, 50));
            Planets.add(new Planet(100, 100, -100, 30, 0, mass_Earth, "Mars", xscreen * 1 / 20, 100, 180, 50));
            /*
             */
            Planets.add(new Planet(xscreen / 4, yscreen / 4, xscreen / 2, 30, 0, mass_Earth, "Mars", xscreen * 1 / 20, 200, 80, 40));
            Planets.add(new Planet(xscreen * 3 / 4, yscreen / 4, xscreen / 2, 30, 0, mass_Earth, "Mars", xscreen * 1 / 20, 200, 80, 40));
            Planets.add(new Planet(xscreen / 4, yscreen * 3 / 4, xscreen / 2, 30, 0, mass_Earth, "Mars", xscreen * 1 / 20, 200, 80, 40));
            Planets.add(new Planet(xscreen * 3 / 4, yscreen * 3 / 4, xscreen / 2, 30, 0, mass_Earth, "Mars", xscreen * 1 / 20, 200, 80, 40));
        }
        if (Symmetry & symmetry == 0) {
            Planets.add(new Planet(xscreen / 2, yscreen / 2, 0, 0, 0, (float) Math.pow(1400, 2), "Sun", (float) 0.00004, 255, 230, 140));
        }
        if (Symmetry & symmetry > 0) {
            collision = false;
            Planets.add(new Planet(xscreen / 2, yscreen / 2, 0, 0, 0, (float) Math.pow(1400, 2), "Sun", (float) 0.00004, 255, 230, 140));
            float mass_Earth = Planets.get(0).mass / 333000;
            angle = 360 / symmetry + rotangle;
            for (int i = 0; i < symmetry; i++) {
                angle += 360 / symmetry;
                distSunx = (float) (symmetrydist * Math.cos(Math.toRadians(angle)));
                distSuny = (float) (symmetrydist * Math.sin(Math.toRadians(angle)));
                Planets.add(new Planet(Planets.get(0).x + distSunx, Planets.get(0).y + distSuny, 0, 30, (float) (angle + 90), mass_Earth, "Earth", xscreen * 1 / 20, 200, 80, 40));
            }
        }
        for (int i = 0; i < Planets.size(); i++) {
            Planets.get(i).x -= xscreen / 2;
            Planets.get(i).y -= yscreen / 2;
            //Planets.get(i).z -= x720;

        }
        Drawobjects.add(Stars.clone());
        Drawobjects.add(Planets.clone());
        update();
        drawBackground();
        draw();
    }

    public void newMenu() {
        int alphaButtons = 100;
        View seek = findViewById(R.id.seekangle);
        View trail = findViewById(R.id.drawtrails);
        View lin = findViewById(R.id.drawlines);
        View coll = findViewById(R.id.collisions);
        View sym = findViewById(R.id.symmetry);
        View ret = findViewById(R.id.reset);
        View scl = findViewById(R.id.screenlimit);
        View symc = findViewById(R.id.symcounter);
        View symcm = findViewById(R.id.symcountermin);
        View drv = findViewById(R.id.drawVel);
        View rot = findViewById(R.id.Rot);
        View acc = findViewById(R.id.accel);
        View ran = findViewById(R.id.ranp);
        View mov = findViewById(R.id.move);
        View zom = findViewById(R.id.zoom);
        View zomseek = findViewById(R.id.seekzoom);
        View SelP = findViewById(R.id.selcp);
        View left = findViewById(R.id.left);
        View right = findViewById(R.id.right);
        View top = findViewById(R.id.top);
        View bottom = findViewById(R.id.bottom);

        if (!Menu) {
            View men = findViewById(R.id.menu);
            men.getBackground().setAlpha(alphaButtons);

            seek.setVisibility(View.INVISIBLE);
            trail.setVisibility(View.INVISIBLE);
            lin.setVisibility(View.INVISIBLE);
            coll.setVisibility(View.INVISIBLE);
            sym.setVisibility(View.INVISIBLE);
            ret.setVisibility(View.INVISIBLE);
            symc.setVisibility(View.INVISIBLE);
            symcm.setVisibility(View.INVISIBLE);
            scl.setVisibility(View.INVISIBLE);
            drv.setVisibility(View.INVISIBLE);
            rot.setVisibility(View.INVISIBLE);
            acc.setVisibility(View.INVISIBLE);
            ran.setVisibility(View.INVISIBLE);
            mov.setVisibility(View.INVISIBLE);
            zom.setVisibility(View.INVISIBLE);
            zomseek.setVisibility(View.INVISIBLE);
            SelP.setVisibility(View.INVISIBLE);
            left.setVisibility(View.INVISIBLE);
            right.setVisibility(View.INVISIBLE);
            top.setVisibility(View.INVISIBLE);
            bottom.setVisibility(View.INVISIBLE);
        }
        if (Menu) {
            View men = findViewById(R.id.menu);
            men.getBackground().setAlpha(alphaButtons);

            seek.setVisibility(View.VISIBLE);
            trail.setVisibility(View.VISIBLE);
            trail.getBackground().setAlpha(alphaButtons);
            lin.setVisibility(View.VISIBLE);
            lin.getBackground().setAlpha(alphaButtons);
            coll.setVisibility(View.VISIBLE);
            coll.getBackground().setAlpha(alphaButtons);
            sym.setVisibility(View.VISIBLE);
            sym.getBackground().setAlpha(alphaButtons);
            ret.setVisibility(View.VISIBLE);
            ret.getBackground().setAlpha(alphaButtons);
            symc.setVisibility(View.VISIBLE);
            symc.getBackground().setAlpha(alphaButtons);
            symcm.setVisibility(View.VISIBLE);
            symcm.getBackground().setAlpha(alphaButtons);
            scl.setVisibility(View.VISIBLE);
            scl.getBackground().setAlpha(alphaButtons);
            drv.setVisibility(View.VISIBLE);
            drv.getBackground().setAlpha(alphaButtons);
            rot.setVisibility(View.VISIBLE);
            rot.getBackground().setAlpha(alphaButtons);
            acc.setVisibility(View.VISIBLE);
            acc.getBackground().setAlpha(alphaButtons);
            ran.setVisibility(View.VISIBLE);
            ran.getBackground().setAlpha(alphaButtons);
            mov.setVisibility(View.VISIBLE);
            mov.getBackground().setAlpha(alphaButtons);
            zom.setVisibility(View.VISIBLE);
            zom.getBackground().setAlpha(alphaButtons);
            zomseek.setVisibility(View.VISIBLE);
            zomseek.getBackground().setAlpha(alphaButtons);
            SelP.setVisibility(View.VISIBLE);
            SelP.getBackground().setAlpha(alphaButtons);

            left.setVisibility(View.VISIBLE);
            right.setVisibility(View.VISIBLE);
            top.setVisibility(View.VISIBLE);
            bottom.setVisibility(View.VISIBLE);
        }
        if (!Symmetry) {
            symcm.setVisibility(View.INVISIBLE);
            symc.setVisibility(View.INVISIBLE);
            seek.setVisibility(View.INVISIBLE);
        }
        if (!Symmetry & ranP & Menu) {
            symcm.setVisibility(View.VISIBLE);
            symc.setVisibility(View.VISIBLE);
        }
        if (Symmetry) {
            ran.setVisibility(View.INVISIBLE);
        }
        if (!Rotate) {
            seek.setVisibility(View.INVISIBLE);
        }
        if (Rotate) {
            seek.setVisibility(View.VISIBLE);
        }
        if (!Zoom) {
            zomseek.setVisibility(View.INVISIBLE);
        }
        if (Zoom) {
            zomseek.setVisibility(View.VISIBLE);
        }
        if (Move) {
            left.setVisibility(View.VISIBLE);
            right.setVisibility(View.VISIBLE);
            top.setVisibility(View.VISIBLE);
            bottom.setVisibility(View.VISIBLE);
        }
        //update();
        draw();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (!Move) {
            update();
            draw();
        }
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (moved) {
                    x2 = (int) event.getX() / (int) zoom; // + x3
                    y2 = (int) event.getY() / (int) zoom; // + y3
                    xactionmove = event.getX();
                    yactionmove = event.getY();
                    moved = false;
                    if (selecPlanet) {
                        //selectplanet();
                        draw();
                    }
                }
                break;
                /*
            case MotionEvent.ACTION_POINTER_DOWN:
                xfinger2 = (int) event.getX() / (int) zoom; // + x3
                yfinger2 = (int) event.getY() / (int) zoom; // + y3
                xactionmovef2 = event.getX();
                yactionmovef2 = event.getY();
                break;
                */
            case MotionEvent.ACTION_MOVE:
                x1 = (int) (event.getX() / zoom); // / (int) zoom;
                y1 = (int) (event.getY() / zoom); // / (int) zoom;
                xactionmove = event.getX();
                yactionmove = event.getY();
                if (Move) {
                    xmove = x1 - x2 + xmove1 - xscreen / 2;
                    ymove = y1 - y2 + ymove1 - yscreen / 2;
                }
                /*
                if (selecPlanet) {
                    selectplanet();
                }
                */
                drawBackground();
                draw();
                break;
            case MotionEvent.ACTION_UP:
                if (!moved) {
                    xmove1 = (xmove + xscreen / 2);// / zoom;// / zoom;
                    ymove1 = (ymove + yscreen / 2);// / zoom;// / zoom;
                    moved = true;
                }
                break;
        }
        return true;
    }

    public void Reset1() {
        //Stars.clear();
        Camerax = 0;
        Cameray = 0;
        Cameraz = xscreen;
        rotangle = 0;
        rotangle = 180;
        zoom = 1;
        symmetrydist = 500;
        Planets.clear();
        for (int i = 0; i < Drawobjects.size(); i++) {
            if (stars < i) {
                Drawobjects.remove(i);
            }
        }
        Create();
    }

    public void selectplanet() {
        for (int i = 0; i < Planets.size(); i++) {
            selectedPlanet = selectedPlanet + 1;
            if (selectedPlanet > Planets.size()) {
                selectedPlanet = 0;
            }

            /*
            float xx = xactionmove;
            float yy = yactionmove;
            float xz = Planets.get(i).x + xmove + xscreen / 2;// * zoom; // funktioniert
            float yz = Planets.get(i).y + ymove + yscreen / 2;// * zoom; // funktioniert
            if (!DDD) {
                xz = zoom * (Planets.get(i).x + xmove) + xscreen / 2;
                yz = zoom * (Planets.get(i).y + ymove) + yscreen / 2;
            }
            if (DDD) {
                xz = Planets.get(i).z / div * zoom * (Planets.get(i).x + xmove) + xscreen / 2;
                yz = Planets.get(i).z / div * zoom * (Planets.get(i).y + ymove) + yscreen / 2;
            }
            float dist = (float) Math.sqrt((Math.pow(xx - xz, 2) + Math.pow(yy - yz, 2)));

            if (dist < (Planets.get(i).radius) * zoom) {
                selecPlanet = true;
                selectedPlanet = i;
                /*
                Planets.get(selectedPlanet).red = 0;
                Planets.get(selectedPlanet).green = 0;
                Planets.get(selectedPlanet).blue = 0;
                */
        }

        //}


    }

    public void update() {
        /*
        if (DDD){
            div = -1 + 2 * (float)Math.cos(xmove) * 100;
        }
        */
        /*
        for (int i = 0; i < Stars.size(); i++) {
            Stars.get(i).x = Stars.get(i).x + Stars.get(i).n;
            if (Stars.get(i).x > xscreen) {
                Stars.get(i).x = 0;
            }
        }

        */
        for (int i = 0; i < Planets.size(); i++) {
            Trailsize = Planets.get(i).Trails.size();
            if (!Canleavescreen & !selecPlanet) {
                if (Planets.get(i).x > xscreen / 2) {
                    Planets.get(i).x = -xscreen / 2;
                }
                if (Planets.get(i).y > yscreen / 2) {
                    Planets.get(i).y = -yscreen / 2;
                }
                if (Planets.get(i).x < -xscreen / 2) {
                    Planets.get(i).x = xscreen / 2;
                }
                if (Planets.get(i).y < -yscreen / 2) {
                    Planets.get(i).y = yscreen / 2;
                }
            }
            /*
            if (!DDD) {
                Planets.get(i).angle1 = (float) Math.atan2(Planets.get(i).y - Planets.get(0).y, Planets.get(i).x - Planets.get(0).x);
            }
            if (DDD) {
                Planets.get(i).angle1 = (float) (Math.atan2(Planets.get(i).y - Planets.get(0).y, Planets.get(i).x - Planets.get(0).x) + Math.atan2(Planets.get(i).y - Planets.get(0).y, Planets.get(i).z - Planets.get(0).z) + Math.atan2(Planets.get(i).x - Planets.get(0).x, Planets.get(i).z - Planets.get(0).z)) / 3;
                Planets.get(i).angle1 = (float) Math.atan2(Planets.get(i).y - Planets.get(0).y, Planets.get(i).x - Planets.get(0).x);
            }
            */
            for (int j = 0; j < Planets.size(); j++) {
                if (i != j) {
                    float dist = 2;
                    if (!DDD) {
                        dist = (float) Math.sqrt(((Planets.get(i).x - Planets.get(j).x) * (Planets.get(i).x - Planets.get(j).x)) + ((Planets.get(i).y - Planets.get(j).y) * (Planets.get(i).y - Planets.get(j).y)));
                    }
                    if (DDD || DDDC) {
                        dist = (float) Math.sqrt(((Planets.get(i).x - Planets.get(j).x) * (Planets.get(i).x - Planets.get(j).x)) + ((Planets.get(i).y - Planets.get(j).y) * (Planets.get(i).y - Planets.get(j).y)) + ((Planets.get(i).z - Planets.get(j).z) * (Planets.get(i).z - Planets.get(j).z)));
                    }
                    float force = 1;
                    if (!DDD) {
                        force = G * (Planets.get(i).mass * Planets.get(j).mass) / (dist * dist * dist);
                    }
                    if (DDD || DDDC) {
                        force = G * (Planets.get(i).mass * Planets.get(j).mass) / (dist * dist * dist);
                    }
                    float nx = (Planets.get(j).x) - (Planets.get(i).x) / dist;
                    float ny = (Planets.get(j).y) - (Planets.get(i).y) / dist;
                    float nz = 0;

                    Planets.get(i).ax += nx * force / (Planets.get(i).mass);
                    Planets.get(i).ay += ny * force / (Planets.get(i).mass);

                    Planets.get(j).ax -= nx * force / (Planets.get(j).mass);
                    Planets.get(j).ay -= ny * force / (Planets.get(j).mass);
                    if (DDD || DDDC) {
                        nz = (Planets.get(j).z) - (Planets.get(i).z) / dist;
                        Planets.get(i).az += nz * force / (Planets.get(i).mass);
                        Planets.get(j).az -= nz * force / (Planets.get(j).mass);
                    }
                    if (dist < Planets.get(i).radius + Planets.get(j).radius & collision) {

                        if (Planets.get(j).mass > Planets.get(i).mass) {
                            Planets.get(j).mass += Planets.get(i).mass;

                            float n = Planets.get(i).mass / Planets.get(j).mass;
                            Planets.get(j).vx += Planets.get(i).vx * n;
                            Planets.get(j).vy += Planets.get(i).vy * n;
                            if (DDD) {
                                Planets.get(j).vz += Planets.get(i).vz * n;
                            }
                            Planets.get(j).red = (int) (Planets.get(j).red + Planets.get(i).red * n);
                            Planets.get(j).green = (int) (Planets.get(j).green + Planets.get(i).green * n);
                            Planets.get(j).blue = (int) (Planets.get(j).blue + Planets.get(i).blue * n);

                            if (Planets.get(j).red > 255) {
                                Planets.get(j).red = 255;
                            }
                            if (Planets.get(j).green > 255) {
                                Planets.get(j).green = 255;
                            }
                            if (Planets.get(j).blue > 255) {
                                Planets.get(j).blue = 255;
                            }

                            Planets.remove(i);
                            removed = i;
                        }
                        if (i != removed) {
                            if (Planets.get(i).mass > Planets.get(j).mass) {
                                Planets.get(i).mass += Planets.get(j).mass;

                                float n = Planets.get(j).mass / Planets.get(i).mass;
                                Planets.get(i).vx += Planets.get(j).vx * n;
                                Planets.get(i).vy += Planets.get(j).vy * n;
                                if (DDD || DDDC) {
                                    Planets.get(i).vz += Planets.get(j).vz * n;
                                }
                                Planets.get(i).red = (int) (Planets.get(i).red + Planets.get(j).red * n);
                                Planets.get(i).green = (int) (Planets.get(i).green + Planets.get(j).green * n);
                                Planets.get(i).blue = (int) (Planets.get(i).blue + Planets.get(j).blue * n);

                                if (Planets.get(i).red > 255) {
                                    Planets.get(i).red = 255;
                                }
                                if (Planets.get(i).green > 255) {
                                    Planets.get(i).green = 255;
                                }
                                if (Planets.get(i).blue > 255) {
                                    Planets.get(i).blue = 255;
                                }
                                Planets.remove(j);
                                removed = j;
                            }

                        }
                    }
                }
                removed = -1;
            }
        }
        for (int i = 0; i < Planets.size(); i++) {
            Planets.get(i).update(updatespeed);
        }
    }

    public void drawBackground() {
        Bitmap bmp1 = Bitmap.createBitmap((int) xscreen, (int) yscreen, Bitmap.Config.ARGB_8888);
        Background.setImageBitmap(bmp1);

        Paint paint = new Paint();
        Canvas canvas = new Canvas(bmp1);
        canvas.drawColor(Color.rgb(0, 0, 0));
        paint.setColor(Color.rgb(255, 255, 255));
        paint.setStrokeWidth(20);
/*
        float xz1 = zoom * (xmove - xscreen / 2) + xscreen / 2;
        float yz1 = zoom * (ymove - yscreen / 2) + yscreen / 2;

        float xz2 = zoom * (xscreen + xmove - xscreen / 2) + xscreen / 2;
        float yz2 = zoom * (yscreen + ymove - yscreen / 2) + yscreen / 2;

        float xz1 = zoom * xmove + xscreen;
        float yz1 = zoom * ymove + yscreen;

        float xz2 = zoom * xmove;
        float yz2 = zoom * ymove;
        */
        //for (int i = 0; i < )

        /*
        canvas.drawLine(xz1, yz1, xz2, yz2, paint);

        for (int i = 0; i < 4; i++) {

            if (xz1 < 0) {
                float xz11 = xz1;
                xz1 = xz2;
                xz2 = 2 * xz2 - xz11;// + xz11;
                paint.setColor(Color.rgb(0, 255, 0));
            }
            if (yz1 < 0) {
                float yz11 = yz1;
                yz1 = yz2;
                yz2 = 2 * yz2 - yz11;// + yz11;
                paint.setColor(Color.rgb(0, 0, 255));
            }
            paint.setStrokeWidth(10);
            canvas.drawLine(xz1, yz1, xz2, yz2, paint);

        }
*/
        for (int i = 0; i < Stars.size(); i++) {
            paint.setColor(Color.argb(255, 255, 255, 255));
            //float xz = Stars.get(i).x;
            //float yz = Stars.get(i).y;
            //canvas.drawCircle(Stars.get(i).x1, Stars.get(i).y1, Stars.get(i).radius, paint);


            //float xz = Stars.get(i).z * zoom + (Stars.get(i).x);// + xscreen ; //+ xmove1
            //float yz = Stars.get(i).z * zoom + (Stars.get(i).y);// + yscreen ; //+ ymove1

            float xz = Stars.get(i).z * div + zoom * (Stars.get(i).x + xmove - xscreen / 2) + xscreen / 2; //
            float yz = Stars.get(i).z * div + zoom * (Stars.get(i).y + ymove - yscreen / 2) + yscreen / 2; //
            /*
            if (xz < 0) {
                xz = xz + xscreen;
            }
            if (yz < 0) {
                yz = yz + yscreen;
            }
            if (xz > zoom * xscreen) {
                xz = xz - xscreen;
            }
            if (yz > zoom * yscreen) {
                yz = yz - yscreen;
            }
            */

            //xz = Stars.get(i).x;
            //yz = Stars.get(i).y;

            //paint.setColor(Color.rgb((int) Stars.get(i).z * 25,0,0));
            canvas.drawCircle(xz, yz, Stars.get(i).radius * zoom, paint);
        }

        Background.setImageBitmap(bmp1);
    }

    public void Cameradraw() { // draw

        Bitmap bmp = Bitmap.createBitmap((int) xscreen, (int) yscreen, Bitmap.Config.ARGB_8888);
        Image.setImageBitmap(bmp);

        Paint paint = new Paint();
        Canvas canvas = new Canvas(bmp);

        LinesPlanets.clear();
        for (int i = 0; i < Planets.size(); i++) {
            //if (i = 0){mache neue Liste}

            if (drawLines) {
                for (int j = 0; j < Planets.size(); j++) {
                    if (i != j & !LinesPlanets.contains(j)) {// & j nicht Element einer Liste
                        //i zu liste hinzufügen
                        LinesPlanets.add(i);
                        float dist = 1;
                        if (!DDD) {
                            dist = (float) Math.sqrt((Math.pow(Planets.get(i).x - Planets.get(j).x, 2) + Math.pow(Planets.get(i).y - Planets.get(j).y, 2)));
                        }
                        if (DDD) {
                            dist = (float) Math.sqrt((Math.pow(Planets.get(i).x - Planets.get(j).x, 2) + Math.pow(Planets.get(i).z - Planets.get(j).z, 2) + Math.pow(Planets.get(i).y - Planets.get(j).y, 2)));
                        }
                        dist = 255 * dist / yscreen;
                        if (dist > 255) {
                            dist = 255;
                        }
                        dist = 255 - dist;
                        paint.setStrokeWidth((Planets.get(i).radius * zoom + Planets.get(j).radius * zoom) / 8);
                        //paint.setColor(Color.argb((int) dist, (int) dist, (int) dist, (int) dist));
                        paint.setColor(Color.argb((int) dist, ((Planets.get(i).red + Planets.get(j).red) / 2), ((Planets.get(i).green + Planets.get(j).green) / 2), ((Planets.get(i).blue + Planets.get(j).blue) / 2)));
                        if (!DDD) {
                            canvas.drawLine(zoom * (Planets.get(i).x + xmove) + xscreen / 2, zoom * (Planets.get(i).y + ymove) + yscreen / 2, zoom * (Planets.get(j).x + xmove) + xscreen / 2, zoom * (Planets.get(j).y + ymove) + yscreen / 2, paint);
                        }
                        if (DDD) {
                            float xz1 = Planets.get(j).z * div + zoom * (Planets.get(j).x + xmove) + xscreen / 2;
                            float yz1 = Planets.get(j).z * div + zoom * (Planets.get(j).y + ymove) + yscreen / 2;
                            float xz = Planets.get(i).z * div + zoom * (Planets.get(i).x + xmove) + xscreen / 2;
                            float yz = Planets.get(i).z * div + zoom * (Planets.get(i).y + ymove) + yscreen / 2;
                            canvas.drawLine(xz, yz, xz1, yz1, paint);
                        }
                    }
                }
            }
            Trailsize = Planets.get(i).TrailX.size();

            paint.setColor(Color.argb(255, 150, 150, 150));

            paint.setColor(Color.argb(255, Planets.get(i).red, Planets.get(i).green, Planets.get(i).blue));
            float xz = 1;
            float yz = 1;
            if (!DDD) {
                xz = zoom * (Planets.get(i).x + xmove) + xscreen / 2;
                yz = zoom * (Planets.get(i).y + ymove) + yscreen / 2;
            }
            if (DDD) {
                xz = Planets.get(i).z * div + zoom * (Planets.get(i).x + xmove) + xscreen / 2;
                yz = Planets.get(i).z * div + zoom * (Planets.get(i).y + ymove) + yscreen / 2;
            }
            canvas.drawCircle(xz, yz, Planets.get(i).radius * zoom, paint);

            for (int k = 0; k < Trailsize; k++) {
                float b = 255 * (k / Trailsize);
                int alpha = (int) (50 + b);
                if (Trailsize == Planets.get(i).trail) {
                    alpha = (int) (255 - b + 50);
                }
                if (alpha > 255) {
                    alpha = 255;
                }
                paint.setColor(Color.argb(alpha, Planets.get(i).red, Planets.get(i).green, Planets.get(i).blue));
                paint.setStrokeWidth(Planets.get(i).radius / 2 * zoom);
                if (k != 0) {
                    float dist = 0;
                    if (!DDD) {
                        dist = (float) Math.sqrt(Math.pow(Planets.get(i).TrailX.get(k) - Planets.get(i).TrailX.get(k - 1), 2) + Math.pow(Planets.get(i).TrailY.get(k) - Planets.get(i).TrailY.get(k - 1), 2));
                    }
                    if (DDD) {
                        dist = (float) Math.sqrt(Math.pow(Planets.get(i).TrailX.get(k) - Planets.get(i).TrailX.get(k - 1), 2) + Math.pow(Planets.get(i).TrailY.get(k) - Planets.get(i).TrailY.get(k - 1), 2) + Math.pow(Planets.get(i).TrailZ.get(k) - Planets.get(i).TrailZ.get(k - 1), 2));
                    }
                    if (drawTrails) { //dist < xscreen / 2 &
                        if (DDD) {
                            xz = Planets.get(i).TrailZ.get(k) * div + zoom * (Planets.get(i).TrailX.get(k) + xmove) + xscreen / 2;
                            yz = Planets.get(i).TrailZ.get(k) * div + zoom * (Planets.get(i).TrailY.get(k) + ymove) + yscreen / 2;
                            float xz1 = Planets.get(i).TrailZ.get(k - 1) * div + zoom * (Planets.get(i).TrailX.get(k - 1) + xmove) + xscreen / 2;
                            float yz1 = Planets.get(i).TrailZ.get(k - 1) * div + zoom * (Planets.get(i).TrailY.get(k - 1) + ymove) + yscreen / 2;
                            canvas.drawLine(xz, yz, xz1, yz1, paint);
                        } else {
                            canvas.drawLine(zoom * (Planets.get(i).TrailX.get(k) + xmove) + xscreen / 2, zoom * (Planets.get(i).TrailY.get(k) + ymove) + yscreen / 2, zoom * (Planets.get(i).TrailX.get(k - 1) + xmove) + xscreen / 2, zoom * (Planets.get(i).TrailY.get(k - 1) + ymove) + yscreen / 2, paint);
                        }
                    }
                }
            }
            xz = Planets.get(i).z * div + zoom * (Planets.get(i).x + xmove) + xscreen / 2;
            yz = Planets.get(i).z * div + zoom * (Planets.get(i).y + ymove) + yscreen / 2;

            if (drawvel) {
                paint.setStrokeWidth(10 * zoom);
                paint.setColor(Color.rgb(200, 0, 0));
                float xz1 = 0;
                float yz1 = 0;

                if (!DDD) {
                    //xz = Planets.get(i).z / div * zoom * (Planets.get(i).TrailX.get(k) + xmove) + xscreen / 2;
                    //yz = Planets.get(i).z / div * zoom * (Planets.get(i).TrailY.get(k) + ymove) + yscreen / 2;
                    xz1 = zoom * (Planets.get(i).x + Planets.get(i).vx + xmove) + xscreen / 2;
                    yz1 = zoom * (Planets.get(i).y + Planets.get(i).vy + ymove) + yscreen / 2;
                }
                if (DDD) {
                    xz1 = Planets.get(i).z * div + zoom * (Planets.get(i).x + Planets.get(i).vx + xmove) + xscreen / 2;
                    yz1 = Planets.get(i).z * div + zoom * (Planets.get(i).y + Planets.get(i).vy + ymove) + yscreen / 2;
                }
                canvas.drawLine(xz, yz, xz1, yz1, paint);
            }
            if (Drawacc) {
                paint.setStrokeWidth(10 * zoom);
                paint.setColor(Color.rgb(0, 200, 0));
                canvas.drawLine(xz, yz, zoom * (Planets.get(i).x + Planets.get(i).ax1 + xmove) + xscreen / 2, zoom * (Planets.get(i).y + Planets.get(i).ay1 + ymove) + yscreen / 2, paint);
            }
        }
        if (selecPlanet) {
            if (selectedPlanet < Planets.size()) {
                paint = new Paint(Color.rgb(Planets.get(selectedPlanet).red, Planets.get(selectedPlanet).green, Planets.get(selectedPlanet).blue));
                float xz = zoom * (Planets.get(selectedPlanet).x + xmove) + xscreen / 2;
                float yz = zoom * (Planets.get(selectedPlanet).y + ymove) + yscreen / 2;

                if (DDD) {
                    xz = Planets.get(selectedPlanet).z / div + zoom * (Planets.get(selectedPlanet).x + xmove) + xscreen / 2;
                    yz = Planets.get(selectedPlanet).z / div + zoom * (Planets.get(selectedPlanet).y + ymove) + yscreen / 2;
                } else {
                    xz = zoom * (Planets.get(selectedPlanet).x + xmove) + xscreen / 2;
                    yz = zoom * (Planets.get(selectedPlanet).y + ymove) + yscreen / 2;
                }
                paint.setTextSize(x720);
                paint.setColor((Color.rgb(Planets.get(selectedPlanet).red, Planets.get(selectedPlanet).green, Planets.get(selectedPlanet).blue)));

                canvas.drawText("Name: " + Planets.get(selectedPlanet).name, xz, yz - x720, paint);
                canvas.drawText("Mass: " + Planets.get(selectedPlanet).mass, xz, yz + x720, paint);
                canvas.drawText("Radius: " + Planets.get(selectedPlanet).radius, xz, yz + x720 * 2, paint);

                //paint.setColor(Color.rgb(Planets.get(selectedPlanet).red, 0, 0));
                canvas.drawText("Red: " + Planets.get(selectedPlanet).red, xz, yz + x720 * 3, paint);

                //paint.setColor(Color.rgb(0, Planets.get(selectedPlanet).green, 0));
                canvas.drawText("Green: " + Planets.get(selectedPlanet).green, xz, yz + x720 * 4, paint);

                //paint.setColor(Color.rgb(0, 0, Planets.get(selectedPlanet).blue));
                canvas.drawText("Blue: " + Planets.get(selectedPlanet).blue, xz, yz + x720 * 5, paint);
            } else {
                selectedPlanet = Planets.size();
            }
        }
        Image.setImageBitmap(bmp);
    }

    public void draw() { //Cameradraw
        Bitmap bmp = Bitmap.createBitmap((int) xscreen, (int) yscreen, Bitmap.Config.ARGB_8888);
        Image.setImageBitmap(bmp);

        Paint paint = new Paint();
        Canvas canvas = new Canvas(bmp);

        //https://www.youtube.com/watch?v=mpTl003EXCY
        float distancecamera = 10;
        float f = 100000; //zoom * 2; // far = 100000
        float n = 1; //zoom * 2; //near = 1
        /*
        Camerax = xscreen / 2  + xsteps; //+ xmove
        Cameray = yscreen / 2  + ysteps; //+ ymove
        Cameraz = -200 + rotangle * 5 + zsteps; //100;//
        */
        Cameray = -500 + zoom * 200; //100;//
        RotationX = (float) Math.toRadians(-ymove * 0.06);
        RotationY = (float) Math.toRadians(xmove * 0.06);

        float w = 1;

        float alpha = (float) Math.toRadians(rotangle / 2);//(float)Math.toRadians(rotangle); //180;//65

        float sinX = (float) Math.sin(RotationX); // Direction Camera
        float cosX = (float) Math.cos(RotationX); // Direction Camera
        float sinY = (float) Math.sin(RotationY); // Direction Camera
        float cosY = (float) Math.cos(RotationY); // Direction Camera


        for (int i = 0; i < Planets.size(); i++) {

            float x = Camerax - Planets.get(i).x;//- xscreen
            float y = Cameray - Planets.get(i).y;//- yscreen
            float z = Cameraz - Planets.get(i).z;


            x = z * sinY + x * cosY;  // rotated coordinates
            z = z * cosY - x * sinY;  // rotated coordinates
            y = y * cosX - z * sinX;  // rotated coordinates
            z = y * sinX + z * cosX;  // rotated coordinates

            float m00 = (float) ((Math.cos(alpha / 2)) / Math.toRadians(Math.sin(alpha / 2)));//Math.acos (alpha/2);//(Math.cos(alpha / 2)) / Math.toRadians(Math.sin(alpha / 2))); //Math.toRadians  //m00 = 200/z; //(1/Math.tan(alpha/2));
            //m00 = 1/xscreen; ///
            //m00 = (float) Math.atan(65*yscreen/xscreen/2);
            float m01 = 0;
            float m02 = 0;
            float m03 = 0;

            float m10 = 0;
            float m11 = m00;
            //m11 = (float) Math.atan(65*xscreen/yscreen/2);
            //m11 = 1/yscreen; ///
            float m12 = 0;
            float m13 = 0;

            float m20 = 0;
            float m21 = 0;
            float m22 = (f + n) / (f - n);
            //m22 = 2/f-n; ///
            float m23 = -1;
            //m23 = 0; ///

            float m30 = 0;
            float m31 = 0;
            float m32 = (2 * f * n) / (f - n);
            //m32 = f + n / f - n; ///
            float m33 = 0;
            //m33 = 1; ///

            //World to Screencoordinates
            float xl = m00 * x + m01 * y + m02 * z + m03 * w; //4x4 Matrix * Point // m00 * x
            float yl = m10 * x + m11 * y + m12 * z + m13 * w; //4x4 Matrix * Point // m11 * y
            float zl = m20 * x + m21 * y + m22 * z + m23 * w; //4x4 Matrix * Point // m22 * z + m23 * w // w = 0
            float wl = m30 * x + m31 * y + m32 * z + m33 * w; //4x4 Matrix * Point // m32 * z


            float x2 = xl / wl + xscreen / 2; //100 * x / wl + xscreen / 2;
            float y2 = yl / wl + yscreen / 2; //100 * y / wl + yscreen / 2;
            /*
            Planets.get(i).drawx = xl / wl + xscreen / 2; ///
            Planets.get(i).drawy = yl / wl + yscreen / 2; ///

            x2 = (float) Math.toDegrees(alpha / 2) *  x / z + xscreen / 2; ///
            y2 = (float) Math.toDegrees(alpha / 2) *  y / z + yscreen / 2; ///
            */
            Planets.get(i).drawx = x2;
            Planets.get(i).drawy = y2;

            Planets.get(i).drawz = zl;
            Planets.get(i).wl = wl;
            /*
            paint.setColor(Color.argb(255, Planets.get(i).red, Planets.get(i).green, Planets.get(i).blue));
            float Rad = Planets.get(i).radius * 500 / wl; //Planets.get(i).radius * 500 / wl

            canvas.drawCircle(x2, y2, Rad, paint);
            */

            /*
            if (drawTrails) {
                for (int j = 1; j < Planets.get(i).TrailX.size(); j++) {

                    x = Camerax - Planets.get(i).TrailX.get(j) - xscreen;
                    y = Cameray - Planets.get(i).TrailY.get(j) - yscreen;
                    z = Cameraz - Planets.get(i).TrailZ.get(j);


                    m00 = (float) Math.acos(alpha / 2); //cot alpha/2
                    m00 = (float) Math.toRadians((Math.cos(alpha / 2)) / Math.toRadians(Math.sin(alpha / 2))); //Math.toRadians

                    m11 = (float) Math.acos(alpha / 2);
                    m11 = m00;

                    m22 = (f + n) / (f - n);
                    m23 = -1;

                    m32 = (2 * f * n) / (f - n);


                    xl = m00 * x + m01 * y + m02 * z + m03 * w; //4x4 Matrix * Point
                    yl = m10 * x + m11 * y + m12 * z + m13 * w; //4x4 Matrix * Point
                    zl = m20 * x + m21 * y + m22 * z + m23 * w; //4x4 Matrix * Point
                    wl = m30 * x + m31 * y + m32 * z + m33 * w; //4x4 Matrix * Point

                    paint.setStrokeWidth(20 * 1000 / wl);
                    paint.setAlpha(255);

                    float xz1 = m00 * x; // (Planets.get(j).x + xmove) + xscreen / 2;
                    float yz1 = m11 * y; // (Planets.get(j).y + ymove) + yscreen / 2;
                    float xz = m00 * Planets.get(i).TrailX.get(j - 1); // Planets.get(i).z * div + zoom * (Planets.get(i).x + xmove) + xscreen / 2;
                    float yz = m11 * Planets.get(i).TrailY.get(j - 1); // Planets.get(i).z * div + zoom * (Planets.get(i).y + ymove) + yscreen / 2;

                    canvas.drawLine(xz, yz, xz1, yz1, paint);
                }

            }
            */
            if (selecPlanet & i == selectedPlanet) {
                paint.setTextSize(x720);
                paint.setColor((Color.rgb(Planets.get(i).red, Planets.get(i).green, Planets.get(i).blue)));

                canvas.drawText("Name: " + Planets.get(i).name, xl, yl - x720, paint);
                canvas.drawText("Mass: " + Planets.get(i).mass, xl, yl + x720, paint);
                canvas.drawText("Radius: " + Planets.get(i).radius, xl, yl + x720 * 2, paint);
                /*
                canvas.drawText("Red: " + Planets.get(i).red, xl, yl + x720 * 3, paint);

                canvas.drawText("Green: " + Planets.get(i).green, xl, yl + x720 * 4, paint);

                canvas.drawText("Blue: " + Planets.get(i).blue, xl, xl + x720 * 5, paint);
                */
                canvas.drawText("X: " + Planets.get(i).x, Planets.get(i).drawx, Planets.get(i).drawy + x720 * 3, paint);
                canvas.drawText("Y: " + Planets.get(i).y, Planets.get(i).drawx, Planets.get(i).drawy + x720 * 4, paint);
                canvas.drawText("Z: " + Planets.get(i).z, Planets.get(i).drawx, Planets.get(i).drawy + x720 * 5, paint);

            }

            //canvas.drawRect(Planets.get(1).x, Planets.get(1).y, Planets.get(2).x, Planets.get(2).y, paint);
            /*
            for (int j = 0; j < Planets.size(); j++) {
                if (i != j) {
                    paint.setColor(Color.argb(255, Planets.get(i).red, Planets.get(i).green, Planets.get(i).blue));
                    canvas.drawLine(Planets.get(i).drawx,Planets.get(i).drawy,Planets.get(j).drawx,Planets.get(j).drawy, paint);

                }
            }
            */
            //canvas.drawRect(Planets.get(1).drawx,Planets.get(1).drawy,Planets.get(2).drawx,Planets.get(2).drawy,Planets.get(3).drawx,Planets.get(3).drawy,Planets.get(4).drawx,Planets.get(4).drawy,paint);
            paint.setColor(Color.argb(255, Planets.get(i).red, Planets.get(i).green, Planets.get(i).blue));
            float Rad = Planets.get(i).radius; //Planets.get(i).radius * 500 / Planets.get(i).wl
            if (Planets.get(i).drawz > distancecamera) {
                canvas.drawCircle(Planets.get(i).drawx, Planets.get(i).drawy, Rad, paint);
            }
        }
        Image.setImageBitmap(bmp);

    }
}
