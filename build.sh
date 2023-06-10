#!/bin/bash

PROJ_ID=lppronouns
PROJ_NAME=LPPronouns
VERSION=1.0.2

# Make directories
mkdir -p ./target/temp_build
cd ./target/temp_build

mkdir -p ./$PROJ_NAME-all/ca/sperrer/p0t4t0sandwich/$PROJ_ID

# --------------------------- Prepare Common --------------------------------

# Prepare bukkit files
cp ../$PROJ_NAME-$VERSION-bukkit.jar ./
mv ./$PROJ_NAME-$VERSION-bukkit.jar ./$PROJ_NAME-$VERSION-bukkit.zip
unzip ./$PROJ_NAME-$VERSION-bukkit.zip -d ./bukkit
rm -rf ./$PROJ_NAME-$VERSION-bukkit.zip

# Copy bukkit files
mv ./bukkit/ca/sperrer/p0t4t0sandwich/$PROJ_ID/bukkit ./$PROJ_NAME-all/ca/sperrer/p0t4t0sandwich/$PROJ_ID
cp ./bukkit/plugin.yml ./$PROJ_NAME-all
rm -rf ./bukkit

# Prepare bungee files
cp ../$PROJ_NAME-$VERSION-bungee.jar ./
mv ./$PROJ_NAME-$VERSION-bungee.jar ./$PROJ_NAME-$VERSION-bungee.zip
unzip ./$PROJ_NAME-$VERSION-bungee.zip -d ./bungee
rm -rf ./$PROJ_NAME-$VERSION-bungee.zip

# Copy bungee files
mv ./bungee/ca/sperrer/p0t4t0sandwich/$PROJ_ID/bungee ./$PROJ_NAME-all/ca/sperrer/p0t4t0sandwich/$PROJ_ID
cp ./bungee/bungee.yml ./$PROJ_NAME-all
rm -rf ./bungee

# Prepare velocity files
cp ../$PROJ_NAME-$VERSION-velocity.jar ./
mv ./$PROJ_NAME-$VERSION-velocity.jar ./$PROJ_NAME-$VERSION-velocity.zip
unzip ./$PROJ_NAME-$VERSION-velocity.zip -d ./velocity
rm -rf ./$PROJ_NAME-$VERSION-velocity.zip

# Copy velocity files
mv ./velocity/ca/sperrer/p0t4t0sandwich/$PROJ_ID/velocity ./$PROJ_NAME-all/ca/sperrer/p0t4t0sandwich/$PROJ_ID
cp ./velocity/velocity.yml ./$PROJ_NAME-all/ca/sperrer/p0t4t0sandwich/$PROJ_ID
cp ./velocity/velocity-plugin.json ./$PROJ_NAME-all
rm -rf ./velocity

# Prepare common files
cp ../$PROJ_NAME-$VERSION-common.jar ./
mv ./$PROJ_NAME-$VERSION-common.jar ./$PROJ_NAME-$VERSION-common.zip
unzip ./$PROJ_NAME-$VERSION-common.zip -d ./common
rm -rf ./$PROJ_NAME-$VERSION-common.zip

# Copy common files
mv ./common/ca/sperrer/p0t4t0sandwich/$PROJ_ID/common ./$PROJ_NAME-all/ca/sperrer/p0t4t0sandwich/$PROJ_ID
mv ./common/ca/sperrer/p0t4t0sandwich/$PROJ_ID/lib ./$PROJ_NAME-all/ca/sperrer/p0t4t0sandwich/$PROJ_ID
cp ./common/config.yml ./$PROJ_NAME-all
cp ./common/LICENSE ./$PROJ_NAME-all
cp ../../README.md ./$PROJ_NAME-all
rm -rf ./common

# --------------------------- Prepare Forge and Fabric --------------------------------

# Prepare Fabric 1.17 files
FABRIC_VERSION=1.17
cp ../$PROJ_NAME-$VERSION-fabric-$FABRIC_VERSION.jar ./
mv ./$PROJ_NAME-$VERSION-fabric-$FABRIC_VERSION.jar ./$PROJ_NAME-$VERSION-fabric-$FABRIC_VERSION.zip
unzip ./$PROJ_NAME-$VERSION-fabric-$FABRIC_VERSION.zip -d ./fabric-$FABRIC_VERSION
rm -rf ./$PROJ_NAME-$VERSION-fabric-$FABRIC_VERSION.zip

# Prepare Fabric 1.20 files
FABRIC_VERSION=1.20
cp ../$PROJ_NAME-$VERSION-fabric-$FABRIC_VERSION.jar ./
mv ./$PROJ_NAME-$VERSION-fabric-$FABRIC_VERSION.jar ./$PROJ_NAME-$VERSION-fabric-$FABRIC_VERSION.zip
unzip ./$PROJ_NAME-$VERSION-fabric-$FABRIC_VERSION.zip -d ./fabric-$FABRIC_VERSION
rm -rf ./$PROJ_NAME-$VERSION-fabric-$FABRIC_VERSION.zip

# Prepare Forge 1.19 files
FORGE_VERSION=1.19
cp ../$PROJ_NAME-$VERSION-forge-$FORGE_VERSION.jar ./
mv ./$PROJ_NAME-$VERSION-forge-$FORGE_VERSION.jar ./$PROJ_NAME-$VERSION-forge-$FORGE_VERSION.zip
unzip ./$PROJ_NAME-$VERSION-forge-$FORGE_VERSION.zip -d ./forge-$FORGE_VERSION
rm -rf ./$PROJ_NAME-$VERSION-forge-$FORGE_VERSION.zip

# Prepare Forge 1.20 files
FORGE_VERSION=1.20
cp ../$PROJ_NAME-$VERSION-forge-$FORGE_VERSION.jar ./
mv ./$PROJ_NAME-$VERSION-forge-$FORGE_VERSION.jar ./$PROJ_NAME-$VERSION-forge-$FORGE_VERSION.zip
unzip ./$PROJ_NAME-$VERSION-forge-$FORGE_VERSION.zip -d ./forge-$FORGE_VERSION
rm -rf ./$PROJ_NAME-$VERSION-forge-$FORGE_VERSION.zip

# --------------------------- Build 1.19 --------------------------------
MC_VERSION=1.19
FABRIC_VERSION=1.17
FORGE_VERSION=1.19
OUT_FILE=$PROJ_NAME-$VERSION-$MC_VERSION

mkdir -p ./$OUT_FILE

# Copy common files
cp -r ./$PROJ_NAME-all/* ./$OUT_FILE/

# Copy fabric files
cp -r ./fabric-$FABRIC_VERSION/ca/sperrer/p0t4t0sandwich/$PROJ_ID/fabric ./$OUT_FILE/ca/sperrer/p0t4t0sandwich/$PROJ_ID
cp ./fabric-$FABRIC_VERSION/fabric.mod.json ./$OUT_FILE
cp ./fabric-$FABRIC_VERSION/$PROJ_ID.mixins.json ./$OUT_FILE
cp -r ./fabric-$FABRIC_VERSION/assets ./$OUT_FILE

# Copy forge files
cp -r ./forge-$FORGE_VERSION/ca/sperrer/p0t4t0sandwich/$PROJ_ID/forge ./$OUT_FILE/ca/sperrer/p0t4t0sandwich/$PROJ_ID
cp ./forge-$FORGE_VERSION/pack.mcmeta ./$OUT_FILE
mkdir -p ./$OUT_FILE/META-INF
cp ./forge-$FORGE_VERSION/META-INF/mods.toml ./$OUT_FILE/META-INF

# Zip Jar contents
cd ./$OUT_FILE
zip -r ../$OUT_FILE.zip ./*
cd ../

# Rename Jar
mv ./$OUT_FILE.zip ./$OUT_FILE.jar

# Generate MD5
md5sum ./$OUT_FILE.jar | cut -d ' ' -f 1 > ./$OUT_FILE.jar.MD5

# Move Jar
mv ./$OUT_FILE.jar ../$OUT_FILE.jar
mv ./$OUT_FILE.jar.MD5 ../$OUT_FILE.jar.MD5

# --------------------------- Build 1.20 --------------------------------
MC_VERSION=1.20
FABRIC_VERSION=1.17
FORGE_VERSION=1.20
OUT_FILE=$PROJ_NAME-$VERSION-$MC_VERSION

mkdir -p ./$OUT_FILE

# Copy common files
cp -r ./$PROJ_NAME-all/* ./$OUT_FILE/

# Copy fabric files
cp -r ./fabric-$FABRIC_VERSION/ca/sperrer/p0t4t0sandwich/$PROJ_ID/fabric ./$OUT_FILE/ca/sperrer/p0t4t0sandwich/$PROJ_ID
cp ./fabric-$FABRIC_VERSION/fabric.mod.json ./$OUT_FILE
cp ./fabric-$FABRIC_VERSION/$PROJ_ID.mixins.json ./$OUT_FILE
cp -r ./fabric-$FABRIC_VERSION/assets ./$OUT_FILE

# Copy forge files
cp -r ./forge-$FORGE_VERSION/ca/sperrer/p0t4t0sandwich/$PROJ_ID/forge ./$OUT_FILE/ca/sperrer/p0t4t0sandwich/$PROJ_ID
cp ./forge-$FORGE_VERSION/pack.mcmeta ./$OUT_FILE
mkdir -p ./$OUT_FILE/META-INF
cp ./forge-$FORGE_VERSION/META-INF/mods.toml ./$OUT_FILE/META-INF

# Zip Jar contents
cd ./$OUT_FILE
zip -r ../$OUT_FILE.zip ./*
cd ../

# Rename Jar
mv ./$OUT_FILE.zip ./$OUT_FILE.jar

# Generate MD5
md5sum ./$OUT_FILE.jar | cut -d ' ' -f 1 > ./$OUT_FILE.jar.MD5

# Move Jar
mv ./$OUT_FILE.jar ../$OUT_FILE.jar
mv ./$OUT_FILE.jar.MD5 ../$OUT_FILE.jar.MD5

# --------------------------- Cleanup --------------------------------
cd ../
rm -rf temp_build
