<template>
    <v-main>
        <v-container class="back" fill-height="fill-height" fluid="fluid">
            <v-row align="center" justify="center">

                <v-col cols="5">
                    <v-card class="card-login mx-a">
                        <v-card-title>비밀번호 찾기</v-card-title>
                        <v-divider></v-divider>
                        <v-card-text class="pa-5">
                            <v-form v-on:submit="find" action="#" method="post">

                                <v-text-field color="green darken-1" v-model="user.email" label="Email"></v-text-field>
                                <v-text-field color="green darken-1" v-model="user.name" label="Name"></v-text-field>
                                <!-- <v-btn class="blue darken-1" large="large" dark="dark"
                                @click="find">확인</v-btn>-->
                                <v-btn
                                    text="text"
                                    color="red"
                                    class="mx-3"
                                    @click="onCancel"
                                    :ripple="{ class: 'red--text' }">
                                    취소
                                </v-btn>
                                <v-btn
                                    text="text"
                                    color="blue"
                                    class="mx-3"
                                    @click="find"
                                    :ripple="{ class: 'blue--text' }">
                                    확인
                                </v-btn>
                            </v-form>
                        </v-card-text>
                    </v-card>
                </v-col>
            </v-row>
        </v-container>
    </v-main>
</template>

<script>
    import {router} from "@/routes.js"
    export default {
        data: () => {
            return {
                user: {
                    email: "",
                    name: ""
                }
            };
        },
        methods: {
            find: function () {
                this
                    .$store
                    .dispatch('userStore/findpassword', this.user);
            },
            onCancel() {
                router.push('/')
            }
        }
    }
</script>

<style></style>