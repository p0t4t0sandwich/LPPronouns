#!/bin/bash

PROJ_ID=lppronouns
PROJ_NAME=LPPronouns
VERSION=1.0.1

# Make directories
mkdir -p ./target/temp_build
cd ./target/temp_build

mkdir -p ./$PROJ_NAME-all/ca/sperrer/p0t4t0sandwich/$PROJ_ID

# Common among all builds

# Prepare bukkit files
cp ../$PROJ_NAME-$VERSION-bukkit.jar ./
mv ./$PROJ_NAME-$VERSION-bukkit.jar ./$PROJ_NAME-$VERSION-bukkit.zip
unzip ./$PROJ_NAME-$VERSION-bukkit.zip -d ./bukkit

# Copy bukkit files
mv ./bukkit/ca/sperrer/p0t4t0sandwich/$PROJ_ID/bukkit ./$PROJ_NAME-all/ca/sperrer/p0t4t0sandwich/$PROJ_ID
cp ./bukkit/plugin.yml ./$PROJ_NAME-all
rm -rf ./bukkit ./$PROJ_NAME-$VERSION-bukkit.zip

# Prepare bungee files
cp ../$PROJ_NAME-$VERSION-bungee.jar ./
mv ./$PROJ_NAME-$VERSION-bungee.jar ./$PROJ_NAME-$VERSION-bungee.zip
unzip ./$PROJ_NAME-$VERSION-bungee.zip -d ./bungee

# Copy bungee files
mv ./bungee/ca/sperrer/p0t4t0sandwich/$PROJ_ID/bungee ./$PROJ_NAME-all/ca/sperrer/p0t4t0sandwich/$PROJ_ID
cp ./bungee/bungee.yml ./$PROJ_NAME-all
rm -rf ./bungee ./$PROJ_NAME-$VERSION-bungee.zip

# Prepare velocity files
cp ../$PROJ_NAME-$VERSION-velocity.jar ./
mv ./$PROJ_NAME-$VERSION-velocity.jar ./$PROJ_NAME-$VERSION-velocity.zip
unzip ./$PROJ_NAME-$VERSION-velocity.zip -d ./velocity

# Copy velocity files
mv ./velocity/ca/sperrer/p0t4t0sandwich/$PROJ_ID/velocity ./$PROJ_NAME-all/ca/sperrer/p0t4t0sandwich/$PROJ_ID
cp ./velocity/velocity.yml ./$PROJ_NAME-all/ca/sperrer/p0t4t0sandwich/$PROJ_ID
cp ./velocity/velocity-plugin.json ./$PROJ_NAME-all
rm -rf ./velocity ./$PROJ_NAME-$VERSION-velocity.zip

# Prepare common files
cp ../$PROJ_NAME-$VERSION-common.jar ./
mv ./$PROJ_NAME-$VERSION-common.jar ./$PROJ_NAME-$VERSION-common.zip
unzip ./$PROJ_NAME-$VERSION-common.zip -d ./common

# Copy common files
mv ./common/ca/sperrer/p0t4t0sandwich/$PROJ_ID/common ./$PROJ_NAME-all/ca/sperrer/p0t4t0sandwich/$PROJ_ID
cp ./common/LICENSE ./$PROJ_NAME-all
cp ../../README.md ./$PROJ_NAME-all
rm -rf ./common ./$PROJ_NAME-$VERSION-common.zip

# ---------------------------
#MC_VERSION=1.19.4
#OUT_FILE=$PROJ_NAME-$VERSION-$MC_VERSION.jar

# Version specific
#mkdir -p ./$PROJ_NAME-$VERSION-$MC_VERSION/ca/sperrer/p0t4t0sandwich/$PROJ_ID

# Fabric and Forge

# Prepare fabric files
#cp ../$PROJ_NAME-$VERSION-fabric-$MC_VERSION.jar ./
#mv ./$PROJ_NAME-$VERSION-fabric-$MC_VERSION.jar ./$PROJ_NAME-$VERSION-fabric-$MC_VERSION.zip
#unzip ./$PROJ_NAME-$VERSION-fabric-$MC_VERSION.zip -d ./fabric-$MC_VERSION

# Copy fabric files
#mv ./fabric-$MC_VERSION/ca/sperrer/p0t4t0sandwich/$PROJ_ID/fabric ./$PROJ_NAME-$VERSION-$MC_VERSION/ca/sperrer/p0t4t0sandwich/$PROJ_ID/fabric
#cp ./fabric-$MC_VERSION/fabric.mod.json ./$PROJ_NAME-$VERSION-$MC_VERSION
#cp ./fabric-$MC_VERSION/$PROJ_NAME.mixins.json ./$PROJ_NAME-$VERSION-$MC_VERSION
#cp -r ./fabric-$MC_VERSION/assets ./$PROJ_NAME-$VERSION-$MC_VERSION
#rm -rf ./fabric-$MC_VERSION ./$PROJ_NAME-$VERSION-fabric-$MC_VERSION.zip

#cp ../target/$PROJ_NAME-$VERSION-forge-$MC_VERSION.jar ./
#mv ./$PROJ_NAME-$VERSION-forge-$MC_VERSION.jar ./$PROJ_NAME-$VERSION-forge-$MC_VERSION.zip
#unzip ./$PROJ_NAME-$VERSION-forge-$MC_VERSION.zip -d ./forge-$MC_VERSION
#
## Process Jars
#cp -r ./fabric/ca/sperrer/p0t4t0sandwich/$PROJ_NAME/fabric ./$PROJ_NAME/ca/sperrer/p0t4t0sandwich/$PROJ_NAME/fabric
#cp ./fabric/fabric.mod.json ./$PROJ_NAME
#cp ./fabric/$PROJ_NAME.mixins.json ./$PROJ_NAME
#cp -r ./fabric/assets ./$PROJ_NAME
#
#cp -r ./forge/ca/sperrer/p0t4t0sandwich/$PROJ_NAME/forge ./$PROJ_NAME/ca/sperrer/p0t4t0sandwich/$PROJ_NAME/forge
#cp ./forge/pack.mcmeta ./$PROJ_NAME
#cp ./forge/META-INF/mods.toml ./$PROJ_NAME/META-INF
#
#
## Zip Jar contents
#cd ./$PROJ_NAME
#zip -r ./$PROJ_NAME.zip ./*
#
## Rename Jar
#mv ./$PROJ_NAME.zip ../../build/libs/$OUT_FILE

#rm -rf ./target/temp_build
